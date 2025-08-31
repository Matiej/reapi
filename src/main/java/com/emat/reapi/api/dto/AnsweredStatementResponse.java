package com.emat.reapi.api.dto;

import java.util.List;

public record AnsweredStatementResponse(
        String statementId,
        String key,
        List<StatementResponse> statementResponseList
) {
}
