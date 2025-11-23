package com.emat.reapi.api.dto;

import com.emat.reapi.submission.Submission;
import com.emat.reapi.submission.SubmissionStatus;

import java.time.Duration;
import java.time.Instant;

public record SubmissionResponse(
        String submissionId,
        String clientId,
        String clientName,
        String testName,
        SubmissionStatus status,
        long remainingSeconds,
        String publicToken,
        Instant createdAt
) {
    public static SubmissionResponse fromDomain(Submission domain) {
        long secs = Duration.between(Instant.now(), domain.expireAt()).getSeconds();
        var remaining = Math.max(0, secs);
        return new SubmissionResponse(
                domain.submissionId(),
                domain.clientId(),
                domain.clientName(),
                domain.testName(),
                domain.status(),
                remaining,
                domain.publicToken(),
                domain.createdAt()
        );
    }
}

