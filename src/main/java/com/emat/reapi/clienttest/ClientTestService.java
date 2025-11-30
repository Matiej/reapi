package com.emat.reapi.clienttest;

import com.emat.reapi.api.dto.clienttestdto.ClientTestSubmissionDto;
import com.emat.reapi.clienttest.domain.ClientTest;
import reactor.core.publisher.Mono;

public interface ClientTestService {
    Mono<ClientTest> getClientTestByToken(String publicToken);

    Mono<Void> saveClientTest(ClientTestSubmissionDto submissionDto);
}
