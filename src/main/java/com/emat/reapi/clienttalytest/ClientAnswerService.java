package com.emat.reapi.clienttalytest;

import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.clienttalytest.domain.ClientAnswer;
import com.emat.reapi.clienttalytest.infra.ClientAnswerShortProjection;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientAnswerService {
    Mono<Void> saveClientAnswers(ClientAnswerDto clientAnswer);
    Mono<List<ClientAnswer>> getAllAnsweredStatements();
    Mono<ClientAnswer> getAnsweredStatementBySubmissionId(String submissionId);
    Mono<ClientAnswer> getAnsweredStatementByClientId(String clientId);
    Flux<ClientAnswerShortProjection> getAllAnsweredShortProjections(Sort sort);
}
