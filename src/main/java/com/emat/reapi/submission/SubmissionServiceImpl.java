package com.emat.reapi.submission;

import com.emat.reapi.api.dto.SubmissionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    @Override
    public Mono<Submission> findBySubmissionId(String submissionId) {
        return submissionRepository.findBySubmissionId(submissionId)
                .switchIfEmpty(Mono.error(new SubmissionException(
                                "Submission not found: " + submissionId,
                                SubmissionException.SubmissionErrorType.SUBMISSION_NOT_FOUND)
                        )
                )
                .map(SubmissionDocument::toDomain);
    }

    @Override
    public Mono<Submission> createSubmission(SubmissionDto request) {
        long seconds = request.durationDays() * 24L * 60L * 60L;
        log.info("Creating submission for clientId: {}", request.clientId());
        var document = new SubmissionDocument();
        document.setSubmissionId("sub_" + UUID.randomUUID());
        document.setClientId(request.clientId());
        document.setClientName(request.clientName());
        document.setClientEmail(request.clientEmail());
        document.setOrderId(request.orderId() + "_" + UUID.randomUUID());
        document.setTestName(request.testName());
        document.setStatus(SubmissionStatus.OPEN);
        document.setDurationDays(request.durationDays());
        document.setPublicToken("pt_" + UUID.randomUUID());
        document.setExpireAt(Instant.now().plusSeconds(seconds));

        return submissionRepository.save(document)
                .map(SubmissionDocument::toDomain)
                .doOnSuccess(sa -> log.info("Submission ID: '{}' created successful", sa.submissionId()))
                .onErrorMap(e -> {
                    log.error("Error creating submission for clientId: {}", request.clientId(), e);
                    return new SubmissionException(
                            "Failed to create submission",
                            e,
                            SubmissionException.SubmissionErrorType.SUBMISSION_CREATE_ERROR
                    );
                });
    }

    @Override
    public Mono<Submission> updateSubmission(SubmissionDto request, String submissionId) {
        return submissionRepository.findBySubmissionId(submissionId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Can't find submissionId: " + submissionId)))
                .map(document -> {
                            document.setClientName(request.clientName());
                            document.setTestName(request.testName());
                            document.setDurationDays(request.durationDays());
                            document.setExpireAt(Instant.now().plusSeconds(request.durationDays() * 60L));
                            return document;
                        }
                )
                .flatMap(submissionRepository::save)
                .map(SubmissionDocument::toDomain)
                .doOnSuccess(suc -> log.info("Submission ID: {} updated successful", submissionId))
                .onErrorMap(e -> {
                    log.error("Error updating submission ID: {}", submissionId, e);
                    return new SubmissionException(
                            "Failed to create submission",
                            e,
                            SubmissionException.SubmissionErrorType.SUBMISSION_UPDATE_ERROR);
                });

    }

    @Override
    public Flux<Submission> findAllByOrderByCreatedAtDesc() {
        return submissionRepository.findAllByOrderByCreatedAtDesc()
                .map(SubmissionDocument::toDomain);
    }

    @Override
    public Mono<Void> deleteSubmission(String submissionId) {
        return submissionRepository.deleteBySubmissionId(submissionId)
                .doOnError(e -> {
                    log.error("Can't delete submissionID: {}", submissionId, e);
                });
    }

    @Override
    public Mono<Submission> closeSubmission(String submissionId) {
        return submissionRepository.findBySubmissionId(submissionId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Can't find submissionId: " + submissionId)))
                .flatMap(document -> {
                            document.setStatus(SubmissionStatus.DONE);
                            return submissionRepository.save(document);
                        }
                )
                .map(SubmissionDocument::toDomain)
                .doOnSuccess(suc -> log.info("Submission ID: {} updated successful", submissionId))
                .onErrorMap(e -> {
                    log.error("Error updating submission ID: {}", submissionId, e);
                    return new SubmissionException(
                            "Failed to create submission",
                            e,
                            SubmissionException.SubmissionErrorType.SUBMISSION_UPDATE_ERROR);
                });
    }
}
