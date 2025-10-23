package com.emat.reapi.profiler.port;

import com.emat.reapi.api.dto.InsightReportDto.ReportJobStatusDto;
import com.emat.reapi.profiler.domain.*;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.domain.reportjob.ReportJob;
import com.emat.reapi.profiler.domain.reportjob.ReportJobStatus;
import com.emat.reapi.statement.domain.*;
import com.emat.reapi.statement.port.ClientAnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
class ProfiledServiceImpl implements ProfiledService {
    private final ClientAnswerService clientAnswerService;
    private final ProfileAnalysisService profileAnalysisService;
    private final ProfileAiAnalysisProcessor profileAiAnalysisProcessor;
    private final ReportJobService reportJobService;

    @Override
    public Mono<ProfiledClientAnswerDetails> getClientProfiledStatements(String clientId) {
        return clientAnswerService.getAnsweredStatementByClientId(clientId)
                .map(this::mapToProfiledClientAnswer);
    }

    @Override
    public Mono<ProfiledClientAnswerDetails> getClientProfiledStatement(String submissionId) {
        return clientAnswerService.getAnsweredStatementBySubmissionId(submissionId)
                .map(this::mapToProfiledClientAnswer)
                .map(this::sortByTotalLimiting);
    }

    @Override
    public Mono<List<ProfiledClientAnswerShort>> getProfiledShort(Sort sort) {
        Sort effectiveSort = (sort == null || sort.isUnsorted())
                ? Sort.by(Sort.Direction.DESC, "submissionDate")
                : sort;

        return clientAnswerService.getAllAnsweredShortProjections(effectiveSort)
                .collectList()
                .flatMap(list -> profileAnalysisService.getAllSubmissionIds()
                        .map(analyzedSet ->
                                list.stream()
                                        .map(p -> new ProfiledClientAnswerShort(
                                                p.getClientName(),
                                                p.getClientId(),
                                                p.getSubmissionId(),
                                                p.getSubmissionDate(),
                                                p.getTestName(),
                                                analyzedSet.contains(p.getSubmissionId())
                                        ))
                                        .toList()
                        )
                )
                .doOnSuccess(list -> log.info("ProfiledShort ready: {} items (sort={})", list.size(), effectiveSort))
                .doOnError(e -> log.error("Error building profiled short list", e));
    }

    @Override
    public Mono<ReportJob> enqueueStatementToAnalyze(String submissionId, PayloadMode mode, boolean force, int retry) {
        return reportJobService.getReportJobBySubmission(submissionId)
                .flatMap(existing -> {
                    boolean isActiveJob = existing.getStatus() == ReportJobStatus.PENDING
                            || existing.getStatus() == ReportJobStatus.RUNNING;
                    if (!force && isActiveJob) {
                        return Mono.just(existing);
                    }
                    existing.setStatus(ReportJobStatus.PENDING);
                    existing.setMode(mode);
                    existing.setError(null);
                    existing.setExpireAt(null);
                    return reportJobService.save(existing);
                })
                .switchIfEmpty(
                        reportJobService.save(ReportJob.builder()
                                .submissionId(submissionId)
                                .mode(mode)
                                .status(ReportJobStatus.PENDING)
                                .expireAt(null)
                                .build()
                        ))
                .doOnSuccess(job -> asyncAnalyzeProfiledStatement(job, retry)
                        .subscribe());
    }

    private Mono<Void> asyncAnalyzeProfiledStatement(ReportJob job, int retry) {
        var submissionId = job.getSubmissionId();
        var mode = job.getMode();
        return reportJobService.save(changeJobStatus(job, ReportJobStatus.RUNNING, null, null))
                .then(getClientProfiledStatement(submissionId))
                .doOnSubscribe(s -> log.info("AI analysis START: submissionId={}, mode={}, retry={}",
                        submissionId,
                        mode,
                        retry))
                .flatMap(statement -> profileAiAnalysisProcessor.sendSubmissionToAI(statement, mode))
                .doOnNext(report -> log.info("AI analysis DONE: submissionId={}, model={}, schema={}/{}",
                        report.getSubmissionId(), report.getModel(), report.getSchemaName(), report.getSchemaVersion()))
                .delayUntil(report -> {
                            log.info("Saving report: submissionId={}, createdAt={}",
                                    report.getSubmissionId(),
                                    report.getCreatedAt());
                            return profileAnalysisService.saveReport(report);
                        }
                )
                .doOnSuccess(r -> log.info("Report saved OK: submissionId={}", r.getSubmissionId()))
                .flatMap(saved -> reportJobService.save(changeJobStatus(
                        job,
                        ReportJobStatus.DONE,
                        Instant.now().plusSeconds(300),
                        null)))
                .retryWhen(
                        Retry.backoff(retry, Duration.ofSeconds(1))
                                .maxBackoff(Duration.ofSeconds(10))
                                .jitter(0.2)
                                .filter(this::isTransientError)
                                .doBeforeRetry(rs -> log.warn(
                                        "Retrying AI analysis: attempt={}, submissionId={}, cause={}",
                                        rs.totalRetries() + 1, submissionId, rs.failure().toString()))
                                .onRetryExhaustedThrow((spec, signal) ->
                                        new ResponseStatusException(
                                                HttpStatus.SERVICE_UNAVAILABLE,
                                                "AI analysis failed after retries for submissionId=" + submissionId,
                                                signal.failure()
                                        )
                                )
                )
                .onErrorResume(ex -> {
                    log.error("`AI analysis ERROR`: submissionId={}, cause={}", submissionId, ex.toString(), ex);
                    return reportJobService.save(changeJobStatus(
                            job,
                            ReportJobStatus.FAILED,
                            Instant.now().plusSeconds(300),
                            ex));
                }).then();
    }

    private ReportJob changeJobStatus(ReportJob job, ReportJobStatus status, Instant expiryAt, Throwable error) {
        job.setStatus(status);
        job.setError(error != null ? error.getMessage() : null);
        job.setExpireAt(expiryAt);
        job.setUpdatedAt(Instant.now());
        return job;
    }

    @Override
    public Mono<ReportJobStatusDto> getLatestAnalysisStatus(String submissionId) {
        return reportJobService.getReportJobBySubmission(submissionId)
                .map(job -> ReportJobStatusDto.from(job, Instant.now()));
    }

    private boolean isTransientError(Throwable t) {
        if (t instanceof TimeoutException) return true;
        if (t instanceof IOException) return true;
        if (t instanceof WebClientResponseException wcre) {

            int code = wcre.getStatusCode().value();
            return code >= 500 || code == 429;
        }
        return false;
    }

    private ProfiledClientAnswerDetails sortByTotalLimiting(ProfiledClientAnswerDetails profiledClientAnswerDetails) {
        List<ProfiledCategoryClientStatements> list = profiledClientAnswerDetails.getProfiledCategoryClientStatementsList()
                .stream()
                .sorted(Comparator.comparingInt(ProfiledCategoryClientStatements::getTotalLimiting))
                .toList()
                .reversed();
        profiledClientAnswerDetails.setProfiledCategoryClientStatementsList(list);
        return profiledClientAnswerDetails;
    }

    //TODO sorting by count
    private ProfiledClientAnswerDetails mapToProfiledClientAnswer(ClientAnswer clientAnswer) {
        return new ProfiledClientAnswerDetails(
                clientAnswer.getClientName(),
                clientAnswer.getClientId(),
                clientAnswer.getSubmissionId(),
                clientAnswer.getSubmissionDate(),
                clientAnswer.getTestName(),
                mapToProfiledCategoryClientStatements(clientAnswer.getClientStatementList())
        );
    }

    private ProfiledClientAnswerShort mapToProfiledClientAnswerShort(ClientAnswer clientAnswer, Boolean isAnalyzed) {
        return new ProfiledClientAnswerShort(
                clientAnswer.getClientName(),
                clientAnswer.getClientId(),
                clientAnswer.getSubmissionId(),
                clientAnswer.getSubmissionDate(),
                clientAnswer.getTestName(),
                isAnalyzed
        );
    }

    private List<ProfiledCategoryClientStatements> mapToProfiledCategoryClientStatements(List<ClientStatement> clientStatements) {
        Map<StatementCategory, List<Statement>> byCategory = clientStatements.stream()
                .collect(Collectors.groupingBy(
                        ClientStatement::getStatementCategory,
                        Collectors.flatMapping(cs -> cs.getStatementList().stream(), Collectors.toList())
                ));

        return Stream.of(StatementCategory.values())
                .map(cat -> buildCategoryBlock(cat, byCategory.getOrDefault(cat, List.of())))
                .toList();
    }

    private ProfiledCategoryClientStatements buildCategoryBlock(StatementCategory cat, List<Statement> statements) {
        int limiting = ProfiledCategoryClientStatements.countTypes(StatementType.LIMITING, statements);
        int supporting = ProfiledCategoryClientStatements.countTypes(StatementType.SUPPORTING, statements);

        List<ProfiledStatement> profiled = statements.stream()
                .map(this::mapToProfiledStatement)
                .toList();

        ProfileCategory category = new ProfileCategory(cat.name(), cat.getPlName());

        return new ProfiledCategoryClientStatements(
                category,
                limiting,
                supporting,
                profiled
        );
    }

    private ProfiledStatement mapToProfiledStatement(Statement s) {
        return new ProfiledStatement(
                s.getStatementDescription(),
                s.getStatementType(),
                s.getStatementStatus()
        );
    }
}
