package com.emat.reapi.statement.port;

import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.statement.domain.ClientAnswer;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientAnswerService {
    Mono<Void> saveClientAnswers(ClientAnswerDto clientAnswer);
    Mono<List<ClientAnswer>> getAllAnsweredStatements();
    Mono<ClientAnswer> getAnsweredStatementBySubmissionId(String submissionId);
    Mono<ClientAnswer> getAnsweredStatementByClientId(String clientId);
}
