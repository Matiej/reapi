package com.emat.reapi.profileanalysis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsightReport {
    private String submissionId;
    private String clientId;
    private String clientName;
    private String testName;
    private String model;
    private PayloadMode payloadMode;
    private String schemaName;
    private String schemaVersion;
    private Instant createdAt;
    private String rawJson;
    @Builder.Default
    private transient InsightReportStructuredAiResponse insightReportStructuredAiResponse = null;

    public static InsightReport of(String submissionId,
                                   String clientId,
                                   String clientName,
                                   String testName,
                                   String model,
                                   PayloadMode mode,
                                   String schemaName,
                                   String schemaVersion,
                                   String rawJson,
                                   Instant createdAt,
                                   InsightReportStructuredAiResponse payload) {
        return InsightReport.builder()
                .submissionId(submissionId)
                .clientId(clientId)
                .clientName(clientName)
                .testName(testName)
                .model(model)
                .payloadMode(mode)
                .schemaName(schemaName)
                .schemaVersion(schemaVersion)
                .createdAt(createdAt)
                .rawJson(rawJson)
                .insightReportStructuredAiResponse(payload)
                .build();
    }
}
