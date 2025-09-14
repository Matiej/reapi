package com.emat.reapi.statement.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClientAnswerRepository extends ReactiveMongoRepository<ClientAnswerDocument, String> {
    Flux<ClientAnswerDocument> findClientAnswerDocumentByClientId(String clientId);

}
