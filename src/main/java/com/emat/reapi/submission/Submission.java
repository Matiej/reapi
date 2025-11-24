package com.emat.reapi.submission;

import java.time.Instant;

public record Submission(
        String id,
        String submissionId,
        String clientId,
        String clientName,
        String clientEmail,
        String orderId,
        String testName,
        SubmissionStatus status,
        int durationDays,
        String publicToken,
        Instant expireAt,
        Instant createdAt
) {
}
