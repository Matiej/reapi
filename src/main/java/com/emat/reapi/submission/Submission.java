package com.emat.reapi.submission;

import java.time.Instant;

public record Submission(
        String id,
        String submissionId,
        String clientId,
        String clientName,
        String testName,
        SubmissionStatus status,
        int duration,
        Instant expireAt,
        Instant createdAt
) {
}
