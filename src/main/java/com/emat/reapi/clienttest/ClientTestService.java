package com.emat.reapi.clienttest;

import com.emat.reapi.clienttest.domain.ClientTest;
import reactor.core.publisher.Mono;

public interface ClientTestService {
    Mono<ClientTest> getClientTestByToken(String publicToken);
}
