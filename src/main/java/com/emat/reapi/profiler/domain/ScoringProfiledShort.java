package com.emat.reapi.profiler.domain;

import java.time.Instant;

public record ScoringProfiledShort(
        String clientName,
        String clientId,
        String submissionId,
        Instant submissionDate,
        String testName,
        int totalScoring,
        int numberOfStatements
) {
}
