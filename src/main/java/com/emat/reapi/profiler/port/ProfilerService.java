package com.emat.reapi.profiler.port;

import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.profiler.domain.ClientAnswer;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfilerService {
    Mono<Void> saveClientAnswers(ClientAnswerDto clientAnswer);
    Mono<List<ClientAnswer>> getAllAnsweredStatements();
}
