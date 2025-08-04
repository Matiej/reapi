package com.emat.reapi.profiler.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StatementDefinitionRepository extends ReactiveMongoRepository<StatementDefinitionDocument, String> {
    Mono<StatementDefinitionDocument> findByStatementId(String statementId);
    Flux<StatementDefinitionDocument> findAllByCategory(String category);
}
