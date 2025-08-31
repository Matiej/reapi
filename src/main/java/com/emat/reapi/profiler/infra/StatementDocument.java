package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.Statement;
import com.emat.reapi.profiler.domain.StatementType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatementDocument {
    private String statementKey;
    private String statementDescription;
    private StatementType statementType;
    private Boolean statementStatus;

    public static StatementDocument toDocument(Statement domain) {
        if (domain == null) {
            return null;
        }
        return new StatementDocument(
                domain.getStatementKey(),
                domain.getStatementDescription(),
                domain.getStatementType(),
                domain.getStatementStatus()
        );
    }

    public Statement toDomain() {
        return new Statement(
                statementKey,
                statementDescription,
                statementType,
                statementStatus
        );
    }
}

