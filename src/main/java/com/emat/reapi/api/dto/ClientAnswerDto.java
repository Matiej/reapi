package com.emat.reapi.api.dto;

import com.emat.reapi.profiler.domain.AnsweredStatement;
import com.emat.reapi.profiler.domain.ClientAnswer;

import java.util.List;

public record ClientAnswerDto(
        String clientId,
        List<AnsweredStatementDto>answeredStatementList
) {
    public ClientAnswer toDomain() {
        List<AnsweredStatement> domainList = answeredStatementList.stream()
                .map(AnsweredStatementDto::toDomain)
                .toList();
        return new ClientAnswer(clientId, domainList);
    }

    public static ClientAnswerDto fromDomain(ClientAnswer domain) {
        List<AnsweredStatementDto> dtoList = domain.getAnsweredStatementList().stream()
                .map(AnsweredStatementDto::toDto)
                .toList();
        return new ClientAnswerDto(domain.getClientId(), dtoList);
    }
}
