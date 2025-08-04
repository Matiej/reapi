package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.ClientAnswer;
import com.emat.reapi.profiler.domain.StatementDefinition;
import com.emat.reapi.profiler.infra.ClientAnswerDocument;
import com.emat.reapi.profiler.infra.ClientAnswerRepository;
import com.emat.reapi.profiler.infra.StatementDefinitionDocument;
import com.emat.reapi.profiler.infra.StatementDefinitionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class ProfilerServiceImpl implements ProfilerService {
    private final StatementDefinitionRepository statementDefinitionRepository;
    private final ClientAnswerRepository clientAnswerRepository;

    @Override
    public Mono<Void> saveClientAnswers(ClientAnswer clientAnswer) {
        log.info("Saving client answers for clientId: {}", clientAnswer.getClientId());
        return clientAnswerRepository.save(ClientAnswerDocument.toDocument(clientAnswer))
                .doOnSuccess(saved -> log.debug("Saved clients answers: {}", clientAnswer))
                .then();
    }

    @Override
    public Mono<StatementDefinition> saveStatementDefinition(StatementDefinition statementDefinition) {
        log.info("Saving statement definition: {}", statementDefinition.getStatementId());
        return statementDefinitionRepository.save(StatementDefinitionDocument.toDocument(statementDefinition))
                .map(StatementDefinitionDocument::toDomain)
                .doOnSuccess(saved -> log.debug("Saved: {}", saved));
    }

    @Override
    public Flux<StatementDefinition> getAllStatementDefinitions() {
        log.info("Retrieving all statement definitions");
        return statementDefinitionRepository.findAll()
                .map(StatementDefinitionDocument::toDomain);
    }

    @Override
    public Flux<StatementDefinition> getStatementDefinitionsByCategory(String category) {
        log.info("Retrieving all statement definitions for category: {}", category);
        return statementDefinitionRepository.findAllByCategory(category)
                .map(StatementDefinitionDocument::toDomain);
    }
}
