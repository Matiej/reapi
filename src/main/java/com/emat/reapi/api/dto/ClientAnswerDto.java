package com.emat.reapi.api.dto;

import com.emat.reapi.clienttalytest.domain.ClientAnswer;
import com.emat.reapi.clienttalytest.domain.ClientStatement;
import com.emat.reapi.statement.domain.StatementDefinition;

import java.time.Instant;
import java.util.List;

public record ClientAnswerDto(
        String clientId,
        String submissionId,
        Instant submissionDate,
        String name,
        String testName,
        List<AnsweredStatementDto> answeredStatementList
) {
    public ClientAnswer toDomain(List<StatementDefinition> statementDefinitionDocuments) {
        List<ClientStatement> domainList = answeredStatementList.stream()
                .map(answeredStatementDto -> answeredStatementDto.toDomain(statementDefinitionDocuments))
                .toList();
        return new ClientAnswer(clientId, submissionId, submissionDate, name, testName, domainList);
    }
}
