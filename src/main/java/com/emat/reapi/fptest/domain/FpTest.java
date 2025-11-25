package com.emat.reapi.fptest.domain;

import java.time.Instant;
import java.util.List;

public record FpTest(
        String id,
        String testId,
        String testName,
        String descriptionBefore,
        String descriptionAfter,
        List<FpTestStatement> fpTestStatements,
        Instant createdAt,
        Instant updatedAt
) {
}
