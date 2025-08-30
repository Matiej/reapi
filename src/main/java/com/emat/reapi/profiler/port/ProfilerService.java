package com.emat.reapi.profiler.port;

import com.emat.reapi.api.dto.ClientAnswerDto;
import reactor.core.publisher.Mono;

public interface ProfilerService {
    Mono<Void> saveClientAnswers(ClientAnswerDto clientAnswer);
}
