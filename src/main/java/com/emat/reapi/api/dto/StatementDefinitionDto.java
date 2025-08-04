package com.emat.reapi.api.dto;


import com.emat.reapi.profiler.domain.StatementDefinition;

public record StatementDefinitionDto(
        String statementId,
        String leftStatement,
        String rightStatement,
        String category
) {
    public static StatementDefinitionDto toDto(StatementDefinition domain) {
        return new StatementDefinitionDto(
                domain.getStatementId(),
                domain.getLeftStatement(),
                domain.getRightStatement(),
                domain.getCategory()
        );
    }

    public StatementDefinition toDomain() {
        return new StatementDefinition(statementId, leftStatement, rightStatement, category);
    }
}
