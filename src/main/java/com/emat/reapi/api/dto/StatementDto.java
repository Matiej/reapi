package com.emat.reapi.api.dto;

public record StatementDto(
        String statementKey,
        String statementDescription,
        Boolean status) {
}
