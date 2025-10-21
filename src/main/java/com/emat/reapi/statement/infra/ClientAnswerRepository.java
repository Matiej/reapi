package com.emat.reapi.statement.infra;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientAnswerRepository extends ReactiveMongoRepository<ClientAnswerDocument, String> {
    Mono<ClientAnswerDocument> findClientAnswerDocumentByClientId(String clientId);
    Mono<ClientAnswerDocument> findClientAnswerDocumentBySubmissionId(String submissionId);

    @Query(value = "{}", fields = "{ clientName:1, clientId:1, submissionId:1, submissionDate:1, testName:1 }")
    Flux<ClientAnswerShortProjection> findAllProjectedBy(Sort sort);

}
