package com.emat.reapi.api.dto.clienttestdto;

public record ClientQuestionDto(
        String id,
        String questionKey,
        String statementCategory,
        String supportingStatement,
        String limitingStatement
) {
}
