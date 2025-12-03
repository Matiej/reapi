package com.emat.reapi.profiler.domain;

import java.time.Instant;

public record ScoringProfiledShort(
        String testSubmissionPublicId,
        String clientName,
        String clientId,
        Instant clientTestDate,
        String submissionId,
        Instant submissionDate,
        String testName,
        int totalScoring,
        int numberOfStatements
) {
}
