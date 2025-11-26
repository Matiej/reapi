package com.emat.reapi.fptest;

import com.emat.reapi.api.dto.fptestdto.FpTestDto;
import com.emat.reapi.fptest.domain.FpTest;
import com.emat.reapi.fptest.domain.FpTestStatement;
import com.emat.reapi.fptest.infra.FpTestDocument;
import com.emat.reapi.fptest.infra.FpTestRepository;
import com.emat.reapi.fptest.infra.FpTestStatementDocument;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.port.StatementDefinitionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
class FpTestServiceImpl implements FpTestService {
    private FpTestRepository fpTestRepository;
    private StatementDefinitionService statementDefinitionService;

    @Override
    public Mono<FpTest> createFpTest(FpTestDto fpTestDto) {
        var testId = "fpt_" + UUID.randomUUID().toString();
        return buildFpTestStatements(fpTestDto.statementKeys())
                .flatMap(fpTestStatementDocuments -> {
                            FpTest domain = new FpTest(
                                    null,
                                    testId,
                                    fpTestDto.testName(),
                                    fpTestDto.descriptionBefore(),
                                    fpTestDto.descriptionAfter(),
                                    fpTestStatementDocuments,
                                    null,
                                    null
                            );
                            return fpTestRepository
                                    .save(FpTestDocument.fromDomain(domain))
                                    .map(FpTestDocument::toDomain);
                        }
                );
    }

    @Override
    public Mono<FpTest> updateFpTest(FpTestDto fpTestDto) {
        if (fpTestDto.testId() == null || fpTestDto.testId().isBlank()) {
            return Mono.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "testId is required for update"
            ));
        }
        return fpTestRepository.findByTestId(fpTestDto.testId())
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Test with testId=" + fpTestDto.testId() + " not found"
                )))
                .zipWith(buildFpTestStatements(fpTestDto.statementKeys()))
                .flatMap((both) -> {
                    List<FpTestStatement> fpTestStatements = both.getT2();
                    FpTestDocument existing = both.getT1();
                    existing.setTestName(fpTestDto.testName());
                    existing.setDescriptionBefore(fpTestDto.descriptionBefore());
                    existing.setDescriptionAfter(fpTestDto.descriptionAfter());
                    existing.setFpTestStatementDocuments(FpTestStatementDocument.fromDomainlist(fpTestStatements));
                    return fpTestRepository.save(existing);
                })
                .map(FpTestDocument::toDomain);
    }

    @Override
    public Flux<FpTest> findAll() {
        return fpTestRepository.findAll()
                .map(FpTestDocument::toDomain);
    }

    @Override
    public Mono<FpTest> findFpTestByTestId(String testId) {
        return fpTestRepository.findByTestId(testId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Test with testId=" + testId + " not found"
                )))
                .map(FpTestDocument::toDomain);
    }

    @Override
    public Mono<Void> deleteFpTestByTestId(String testId) {
        return fpTestRepository.findByTestId(testId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Test with testId=" + testId + " not found"
                )))
                .flatMap(doc -> fpTestRepository.deleteByTestId(testId));
    }

    @Override
    public Flux<FpTestStatement> getAllTestStatements() {
        return statementDefinitionService.getAllStatementDefinitions()
                .map(FpTestStatement::formStatementDefinition);
    }

    private Mono<List<FpTestStatement>> buildFpTestStatements(List<String> statementKeys) {
        return statementDefinitionService.getAllStatementDefinitions()
                .collectMap(StatementDefinition::getStatementKey)
                .flatMap(definitionsByKey -> {
                    List<String> missingKeys = statementKeys.stream()
                            .filter(key -> !definitionsByKey.containsKey(key))
                            .toList();
                    if (!missingKeys.isEmpty()) {
                        String msg = "Keys not found in statement definitions: " +
                                String.join(", ", missingKeys);
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, msg));
                    }
                    List<FpTestStatement> statements = statementKeys.stream()
                            .map(definitionsByKey::get)
                            .map(FpTestStatement::formStatementDefinition) // -> FpTestStatementDocument
                            .map(doc -> new FpTestStatement(
                                    doc.statementKey(),
                                    doc.statementsDescription(),
                                    doc.statementsCategory()
                            ))
                            .collect(Collectors.toList());
                    return Mono.just(statements);
                });
    }
}
