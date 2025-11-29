package com.emat.reapi.clienttest.domain;

import java.time.Instant;

public record ClientTestAnswerSubmission(
        String id,
        String clientTestSubmissionPublicId,
        String clientId,
        String clientName,
        String clientEmail,
        String submissionId,
        Instant submissionDate,
        String testId,
        String testName,
        String publicToken

) {
}
