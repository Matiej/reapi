package com.emat.reapi.clienttest.domain;

import com.emat.reapi.statement.domain.StatementCategory;

public record ClientTestQuestion(
        String id,
        String questionKey,
        StatementCategory statementCategory,
        String supportingStatement,
        String limitingStatement
        ) {
}
