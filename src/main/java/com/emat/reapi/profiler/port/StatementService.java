package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.StatementDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatementService {

    Mono<StatementDefinition> saveStatementDefinition(StatementDefinition statementDefinition);
    Flux<StatementDefinition > getAllStatementDefinitions();
    Flux<StatementDefinition> getStatementDefinitionsByCategory(String category);
}
