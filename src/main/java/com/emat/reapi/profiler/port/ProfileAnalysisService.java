package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.report.InsightReport;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface ProfileAnalysisService {
    Mono<List<InsightReport>> getAnalysis(String submissionId);
    Mono<Void> saveReport(InsightReport insightReport);
    Mono<Set<String>> getAllSubmissionIds();
}
