package com.emat.reapi.api.dto.clienttestdto;

public record ClientQuestionDto(
        String id,
        String statementKey,
        String statementCategory,
        String supportingStatement,
        String limitingStatement
) {
}
