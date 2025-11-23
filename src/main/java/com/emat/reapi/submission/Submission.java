package com.emat.reapi.submission;

import java.time.Instant;

public record Submission(
        String id,
        String submissionId,
        String clientId,
        String clientName,
        String testName,
        SubmissionStatus status,
        int durationMinutes,
        String publicToken,
        Instant expireAt,
        Instant createdAt
) {
}
