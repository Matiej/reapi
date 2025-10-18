package com.emat.reapi.profiler.domain.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsightReportAiResponse {
    String clientName;
    String clientId;
    String submissionId;
    Instant date;
    String testName;
    String model;
    String schemaName;
    String schemaVersion;
    String rawJson;
    InsightReportStructuredAiResponse insightReportStructuredAiResponse;
}
