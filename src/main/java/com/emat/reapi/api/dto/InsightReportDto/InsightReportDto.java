package com.emat.reapi.api.dto.InsightReportDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public record InsightReportDto(
        String submissionId,
        String clientId,
        String clientName,
        String testName,
        String model,
        String schemaName,
        String schemaVersion,
        Instant createdAt,
        @JsonIgnore
        String rawJson,
        InsightReportStructuredAiResponseDto insightReportStructuredAiResponseDto
) {
}
