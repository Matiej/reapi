package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.infra.InsightReportDocument;
import com.emat.reapi.profiler.infra.InsightReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ProfileAnalysisServiceImpl implements ProfileAnalysisService {
    private final InsightReportRepository reportRepository;

    @Override
    public Mono<Void> saveReport(InsightReport insightReport) {
        return reportRepository.save(InsightReportDocument.from(insightReport)).then();
    }

    @Override
    public Mono<List<InsightReport>> getAnalysis(String submissionId) {
        log.info("Trying to fetch from reports for submissionId: {}", submissionId);
        return reportRepository.findBySubmissionIdOrderByCreatedAtDesc(submissionId)
                .collectList()
                .map(results -> results.
                        stream()
                        .map(InsightReportDocument::toDomain)
                        .toList())
                .doOnSuccess(result -> log.info("Fetched {} reports from data base for submissionId: {}",
                        result.isEmpty() ? 0 : result.size(),
                        submissionId));
    }

    @Override
    public Mono<Set<String>> getAllSubmissionIds() {
        return reportRepository.distinctSubmissionIdsAll().collectList().map(HashSet::new);
    }
}

