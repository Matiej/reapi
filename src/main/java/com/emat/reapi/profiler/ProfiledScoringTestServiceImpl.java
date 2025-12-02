package com.emat.reapi.profiler;

import com.emat.reapi.clienttest.ClientTestService;
import com.emat.reapi.clienttest.domain.ClientTestAnswer;
import com.emat.reapi.clienttest.domain.ClientTestSubmission;
import com.emat.reapi.fptest.FpTestService;
import com.emat.reapi.profiler.domain.*;
import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.submission.SubmissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProfiledScoringTestServiceImpl implements ProfiledScoringTestService {
    private final ClientTestService clientTestService;
    private final SubmissionService submissionService;
    private final FpTestService fpTestService;

    @Override
    public Mono<ScoringProfiledClientDetails> getScoringProfile(String submissionId) {
        return null;
    }

    @Override
    public Mono<List<ScoringProfiledShort>> getScoringShortProfiles() {
        return null;
    }

    private ScoringProfiledClientDetails mapToProfile(ClientTestSubmission submission) {
        Map<StatementCategory, List<ClientTestAnswer>> byCategory =
                submission.getClientTestAnswerList().stream()
                        .collect(Collectors.groupingBy(ClientTestAnswer::category));

        ScoringOverallSummary overall = buildOverallSummary(submission.getClientTestAnswerList());

        List<ScoringCategoryBlock> categories = byCategory.entrySet().stream()
                .map(entry -> buildCategoryBlock(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(ScoringCategoryBlock::getTotalScore)) // rosnąco
                .toList();

        return new ScoringProfiledClientDetails(
                submission.getClientName(),
                submission.getClientId(),
                submission.getSubmissionId(),
                submission.getSubmissionDate(),
                submission.getTestId(),
                submission.getTestName(),
                submission.getCreatedAt(),
                overall,
                categories
        );
    }

    private ScoringOverallSummary buildOverallSummary(List<ClientTestAnswer> answers) {
        Map<String, Long> buckets = answers.stream()
                .collect(Collectors.groupingBy(
                        answer -> String.valueOf(answer.scoring()),
                        Collectors.counting()
                ));

        int totalScore = answers.stream()
                .mapToInt(ClientTestAnswer::scoring)
                .sum();

        return new ScoringOverallSummary(
                answers.size(),
                totalScore,
                buckets
        );
    }

    private ScoringCategoryBlock buildCategoryBlock(
            StatementCategory category,
            List<ClientTestAnswer> answers
    ) {
        int totalScore = answers.stream()
                .mapToInt(ClientTestAnswer::scoring)
                .sum();

        Map<String, Long> buckets = answers.stream()
                .collect(Collectors.groupingBy(
                        answer -> String.valueOf(answer.scoring()),
                        Collectors.counting()));

        List<ScoringStatementPair> pairs = answers.stream()
                .map(answer -> new ScoringStatementPair(
                        answer.statementKey(),
                        answer.limitingDescription(),
                        answer.supportingDescription(),
                        answer.scoring()
                ))
                .sorted(Comparator.comparingInt(ScoringStatementPair::getScoring))
                .toList();

        ProfileCategory profileCategory = new ProfileCategory(
                category.name(),
                category.getPlName()
        );

        double avgScore = answers.isEmpty()
                ? 0.0
                : (double) totalScore / answers.size();

        return new ScoringCategoryBlock(
                profileCategory,
                answers.size(),
                totalScore,
                avgScore,
                buckets,
                pairs
        );
    }
}
