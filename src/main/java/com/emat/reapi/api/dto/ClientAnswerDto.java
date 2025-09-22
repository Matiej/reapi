package com.emat.reapi.api.dto;

import com.emat.reapi.statement.domain.ClientAnswer;
import com.emat.reapi.statement.domain.ClientStatement;

import java.time.Instant;
import java.util.List;

public record ClientAnswerDto(
        String clientId,
        String submissionId,
        Instant submissionDate,
        String name,
        String testName,
        List<AnsweredStatementDto>answeredStatementList
) {
    public ClientAnswer toDomain() {
        List<ClientStatement> domainList = answeredStatementList.stream()
                .map(AnsweredStatementDto::toDomain)
                .toList();
        return new ClientAnswer(clientId, submissionId, submissionDate, name, testName, domainList);
    }
}
