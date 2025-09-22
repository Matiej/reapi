package com.emat.reapi.api.dto;

public record StatementResponse (
        String statementKey,
        String statementDescription,
        Boolean status,
        String statementType
) {
}
