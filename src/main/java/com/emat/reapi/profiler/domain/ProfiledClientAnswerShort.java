package com.emat.reapi.profiler.domain;

import java.time.Instant;

public record ProfiledClientAnswerShort(
        String clientName,
        String clientId,
        String submissionId,
        Instant submissionDate,
        String testName
) {
}
