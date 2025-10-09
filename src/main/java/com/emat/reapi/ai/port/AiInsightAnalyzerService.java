package com.emat.reapi.ai.port;

import com.emat.reapi.profiler.domain.MinimizedPayload;
import com.emat.reapi.profiler.domain.report.InsightReportAiResponse;
import reactor.core.publisher.Mono;

public interface AiInsightAnalyzerService {
    Mono<InsightReportAiResponse> analyze(MinimizedPayload payload);
}
