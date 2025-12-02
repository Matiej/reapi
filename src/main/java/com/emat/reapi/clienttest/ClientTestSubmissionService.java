package com.emat.reapi.clienttest;

import com.emat.reapi.clienttest.domain.ClientTestSubmission;
import reactor.core.publisher.Mono;

public interface ClientTestSubmissionService {
    Mono<ClientTestSubmission> findClientTestBySubmissionId(String submissionId);
}
