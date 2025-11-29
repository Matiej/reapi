package com.emat.reapi.clienttest.domain;

import java.util.List;

public record ClientTest(
        String testName,
        String descriptionBefore,
        String descriptionAfter,
        String publicToken,
        String submissionId,
        List<ClientTestQuestion> clientTestQuestions
) {
}
