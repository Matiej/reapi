package com.emat.reapi.clienttest;

import com.emat.reapi.clienttest.domain.ClientTest;
import com.emat.reapi.clienttest.domain.ClientTestQuestion;
import com.emat.reapi.fptest.FpTestService;
import com.emat.reapi.fptest.domain.FpTest;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.domain.StatementType;
import com.emat.reapi.statement.domain.StatementTypeDefinition;
import com.emat.reapi.statement.port.StatementDefinitionService;
import com.emat.reapi.submission.SubmissionService;
import com.emat.reapi.submission.domain.Submission;
import com.emat.reapi.submission.domain.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ClientTestServiceImpl implements ClientTestService {
    private SubmissionService submissionService;
    private FpTestService fpTestService;
    private StatementDefinitionService statementDefinitionService;

    @Override
    public Mono<ClientTest> getClientTestByToken(String publicToken) {
        return submissionService.findByPublicTokenAndStatus(publicToken, SubmissionStatus.OPEN)
                .switchIfEmpty(
                        Mono.defer(() -> {
                                    log.warn("Can't find open submission for publicToken: {}", publicToken);
                                    return Mono.error(new ClientTestException("Can't find open submission for publicToken: " + publicToken));
                                }
                        )
                ).flatMap(sub -> Mono.zip(
                        Mono.just(sub),
                        fpTestService.getFpTesByTestId(sub.testId())
                                .switchIfEmpty(
                                        Mono.defer(() -> {
                                                    String testId = sub.testId();
                                                    log.warn("Can't find FpTest for testId: {}", testId);
                                                    return Mono.error(new ClientTestException("Can't find FpTest for testId: " + testId));
                                                }
                                        )
                                ))
                ).flatMap(both -> {
                    Submission submission = both.getT1();
                    FpTest fpTest = both.getT2();
                    return statementDefinitionService.getAllStatementDefinitions()
                            .collectList()
                            .map(defList -> fpTest.fpTestStatements()
                                    .stream()
                                    .map(fpTestStatement -> findDefByStatementKey(fpTestStatement.statementKey(), defList))
                                    .toList()
                            ).map(definitions -> {
                                List<ClientTestQuestion> clientTestQuestions = definitions
                                        .stream()
                                        .map(this::mapToTestQuestions)
                                        .toList();
                                return new ClientTest(
                                        fpTest.testName(),
                                        fpTest.descriptionBefore(),
                                        fpTest.descriptionAfter(),
                                        submission.publicToken(),
                                        submission.submissionId(),
                                        clientTestQuestions
                                );
                            });
                });

    }

    @SneakyThrows
    private StatementDefinition findDefByStatementKey(String statementKey, List<StatementDefinition> statementDefinitions) {
        return statementDefinitions
                .stream()
                .filter(p -> p.getStatementKey().equalsIgnoreCase(statementKey))
                .findFirst()
                .orElseThrow(() -> new ClientTestException("Can't find statement definition for key: " + statementKey));
    }

    private ClientTestQuestion mapToTestQuestions(StatementDefinition statementDefinition) {
        StatementTypeDefinition supportingDef = getStatementType(StatementType.SUPPORTING, statementDefinition.getStatementTypeDefinitions());
        StatementTypeDefinition limitingDef = getStatementType(StatementType.LIMITING, statementDefinition.getStatementTypeDefinitions());
        return new ClientTestQuestion(
                statementDefinition.getStatementId(),
                statementDefinition.getStatementKey(),
                statementDefinition.getCategory(),
                supportingDef.getStatementDescription(),
                limitingDef.getStatementDescription()
        );
    }

    @SneakyThrows
    private StatementTypeDefinition getStatementType(StatementType type, List<StatementTypeDefinition> statementTypeDefinitions) {
        return statementTypeDefinitions
                .stream()
                .filter(stType -> stType.getStatementType() == type)
                .findFirst()
                .orElseThrow(() -> new ClientTestException("Can't find statement type: " + type.name()));
    }

}

