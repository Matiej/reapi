package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.ProfileCategory;
import com.emat.reapi.profiler.domain.ProfiledCategoryClientStatements;
import com.emat.reapi.profiler.domain.ProfiledClientAnswer;
import com.emat.reapi.profiler.domain.ProfiledStatement;
import com.emat.reapi.statement.domain.*;
import com.emat.reapi.statement.port.ClientAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
class ProfiledServiceImpl implements ProfiledService {
    private final ClientAnswerService clientAnswerService;

    @Override
    public Mono<ProfiledClientAnswer> getClientProfiledStatements(String clientId) {
        return clientAnswerService.getAnsweredStatementByClientId(clientId)
                .map(this::mapToProfiledClientAnswer);
    }

    @Override
    public Mono<ProfiledClientAnswer> getClientProfiledStatement(String submissionId) {
        return clientAnswerService.getAnsweredStatementBySubmissionId(submissionId)
                .map(this::mapToProfiledClientAnswer)
                .map(this::sortByTotalLimiting);
    }

    private ProfiledClientAnswer sortByTotalLimiting(ProfiledClientAnswer profiledClientAnswer) {
        List<ProfiledCategoryClientStatements> list = profiledClientAnswer.getProfiledCategoryClientStatementsList()
                .stream()
                .sorted(Comparator.comparingInt(ProfiledCategoryClientStatements::getTotalLimiting))
                .toList()
                .reversed();
        profiledClientAnswer.setProfiledCategoryClientStatementsList(list);
        return profiledClientAnswer;
    }

    //TODO sorting by count
    private ProfiledClientAnswer mapToProfiledClientAnswer(ClientAnswer clientAnswer) {
        return new ProfiledClientAnswer(
                clientAnswer.getClientName(),
                clientAnswer.getClientId(),
                clientAnswer.getSubmissionId(),
                clientAnswer.getSubmissionDate(),
                clientAnswer.getTestName(),
                mapToProfiledCategoryClientStatements(clientAnswer.getClientStatementList())
        );
    }

    private List<ProfiledCategoryClientStatements> mapToProfiledCategoryClientStatements(List<ClientStatement> clientStatements) {
        Map<StatementCategory, List<Statement>> byCategory = clientStatements.stream()
                .collect(Collectors.groupingBy(
                        ClientStatement::getStatementCategory,
                        Collectors.flatMapping(cs -> cs.getStatementList().stream(), Collectors.toList())
                ));

        return Stream.of(StatementCategory.values())
                .map(cat -> buildCategoryBlock(cat, byCategory.getOrDefault(cat, List.of())))
                .toList();
    }

    private ProfiledCategoryClientStatements buildCategoryBlock(StatementCategory cat, List<Statement> statements) {
        int limiting = ProfiledCategoryClientStatements.countTypes(StatementType.LIMITING, statements);
        int supporting = ProfiledCategoryClientStatements.countTypes(StatementType.SUPPORTING, statements);

        List<ProfiledStatement> profiled = statements.stream()
                .map(this::mapToProfiledStatement)
                .toList();

        ProfileCategory category = new ProfileCategory(cat.name(), cat.getPlName());

        return new ProfiledCategoryClientStatements(
                category,
                limiting,
                supporting,
                profiled
        );
    }

    private ProfiledStatement mapToProfiledStatement(Statement s) {
        return new ProfiledStatement(
                s.getStatementDescription(),
                s.getStatementType(),
                s.getStatementStatus()
        );
    }
}
