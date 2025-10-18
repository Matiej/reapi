package com.emat.reapi.statement.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientAnswerRepository extends ReactiveMongoRepository<ClientAnswerDocument, String> {
    Mono<ClientAnswerDocument> findClientAnswerDocumentByClientId(String clientId);
    Mono<ClientAnswerDocument> findClientAnswerDocumentBySubmissionId(String submissionId);

}
