package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.domain.report.InsightReportAiResponse;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import reactor.core.publisher.Mono;

public interface ProfileAnalysisService {
    Mono<InsightReportAiResponse> analyzeSubmission(String submissionId, boolean force, PayloadMode mode, int retry);
    Mono<InsightReport> getAnalysis(String submissionId);
}
