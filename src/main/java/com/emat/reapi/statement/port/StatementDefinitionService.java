package com.emat.reapi.statement.port;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StatementDefinitionService {

    Mono<StatementDefinition> saveStatementDefinition(StatementDefinition statementDefinition);
    Flux<StatementDefinition > getAllStatementDefinitions();
    Mono<List<StatementDefinition>> getStatementDefinitionsByCategory(StatementCategory category);
}
