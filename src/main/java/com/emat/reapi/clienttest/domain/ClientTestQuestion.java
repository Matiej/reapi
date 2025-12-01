package com.emat.reapi.clienttest.domain;

import com.emat.reapi.statement.domain.StatementCategory;

public record ClientTestQuestion(
        String id,
        String statementKey,
        StatementCategory statementCategory,
        String supportingStatement,
        String limitingStatement
        ) {
}
