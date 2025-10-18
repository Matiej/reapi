package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.domain.report.InsightReportAiResponse;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfileAnalysisService {
    Mono<InsightReportAiResponse> analyzeSubmission(String submissionId, boolean force, PayloadMode mode, int retry);
    Mono<List<InsightReport>> getAnalysis(String submissionId);
}
