package com.emat.reapi.statement.port;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.infra.ClientAnswerRepository;
import com.emat.reapi.statement.infra.StatementDefinitionDocument;
import com.emat.reapi.statement.infra.StatementDefinitionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.emat.reapi.statement.infra.StatementDefinitionsDictionary.ALL;

@Service
@Slf4j
@AllArgsConstructor
public class StatementDefinitionServiceImpl implements StatementDefinitionService {
    private final StatementDefinitionRepository statementDefinitionRepository;
    private final ClientAnswerRepository clientAnswerRepository;

    @Override
    public Mono<StatementDefinition> saveStatementDefinition(StatementDefinition statementDefinition) {
        log.info("Saving fistStatement definition: {}", statementDefinition.getStatementId());
        return statementDefinitionRepository.save(StatementDefinitionDocument.toDocument(statementDefinition))
                .map(StatementDefinitionDocument::toDomain)
                .doOnSuccess(saved -> log.debug("Saved: {}", saved));
    }

    @Override
    public Flux<StatementDefinition> getAllStatementDefinitions() {
        log.info("Retrieving all fistStatement definitions");
        return statementDefinitionRepository.findAll()
                .map(StatementDefinitionDocument::toDomain);
    }

    @Override
    public Mono<List<StatementDefinition>> getStatementDefinitionsByCategory(StatementCategory category) {
        log.info("Retrieving all fistStatement definitions for category: {}", category);

        return Mono.just(ALL.stream().filter(p-> p.getCategory().equals(category)).toList());

//        return statementDefinitionRepository.findAllByCategory(category.name())
//                .map(StatementDefinitionDocument::toDomain);
    }
}
