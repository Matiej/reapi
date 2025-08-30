package com.emat.reapi.api.dto;

import com.emat.reapi.profiler.domain.ClientAnswer;
import com.emat.reapi.profiler.domain.ClientStatement;

import java.util.List;

public record ClientAnswerDto(
        String clientId,
        String submissionId,
        String name,
        String testName,
        List<AnsweredStatementDto>answeredStatementList
) {
    public ClientAnswer toDomain() {
        List<ClientStatement> domainList = answeredStatementList.stream()
                .map(AnsweredStatementDto::toDomain)
                .toList();
        return new ClientAnswer(clientId, submissionId, name, testName, domainList);
    }

//    public static ClientAnswerDto toDto(ClientAnswer domain) {
//        List<AnsweredStatementDto> dtoList = domain.getClientStatementList().stream()
//                .map(AnsweredStatementDto::toDto)
//                .toList();
//        return new ClientAnswerDto(domain.getClientId(), domain.getName(), dtoList);
//    }
}
