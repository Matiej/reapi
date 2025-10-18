package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.report.InsightReport;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfileAnalysisService {
    Mono<List<InsightReport>> getAnalysis(String submissionId);
    Mono<Boolean> isAnalysed(String submissionId);
    Mono<Void> saveReport(InsightReport insightReport);
}
