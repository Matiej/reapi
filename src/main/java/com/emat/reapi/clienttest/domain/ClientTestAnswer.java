package com.emat.reapi.clienttest.domain;

import com.emat.reapi.statement.domain.StatementCategory;

public record ClientTestAnswer(
        String statementKey,
        StatementCategory category,
        String limitingDescription,
        String supportingDescription,
        int scoring
) {
}
