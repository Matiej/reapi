package com.emat.reapi.api.dto;

import com.emat.reapi.statement.domain.ClientAnswer;
import com.emat.reapi.statement.domain.StatementMapper;

import java.util.List;

public record ClientAnswerResponse(
        String publicId,
        String clientId,
        String submissionId,
        String submissionDate,
        String name,
        String testName,
        String date,
        List<AnsweredStatementResponse> answeredStatementResponseList
) {
    public static ClientAnswerResponse fromDomain(ClientAnswer answer) {
        return new ClientAnswerResponse(
                answer.getPublicId(),
                answer.getClientId(),
                answer.getSubmissionId(),
                answer.getSubmissionDate().toString(),
                answer.getClientName(),
                answer.getTestName(),
                answer.getDate().toString(),
                answer.getClientStatementList().stream()
                        .map(StatementMapper::toAnsweredStatementResponse)
                        .toList()
        );
    }
}
