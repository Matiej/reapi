package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.*;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.statement.domain.StatementType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MinimizerServiceImpl implements MinimizerService {

    private static final int MINIMAL_PER_TYPE = 2;    // LIMITING / SUPPORTING w trybie MINIMAL
    private static final int ENRICHED_PER_TYPE = 4;   // dla "najgorszych" kategorii w ENRICHED
    private static final int ENRICHED_TOP_K = 2; //ile kategorii wzbogacić

    @Override
    public Mono<MinimizedPayload> minimize(ProfiledClientAnswerDetails profiledClientAnswerDetails, PayloadMode mode) {
//        Objects.requireNonNull(pca, "ProfiledClientAnswer cannot be null");
        if (profiledClientAnswerDetails.getProfiledCategoryClientStatementsList() == null) {
            return Mono.just(emptyFor(profiledClientAnswerDetails));
        }

        // wybór kategorii do wzbogacenia w ENRICHED (najniższy balanceIndex, potem najwięcej aktywnych punktów)
        final Set<String> enrichedCategories = (mode == PayloadMode.ENRICHED)
                ? pickWorstCategoriesByBalance(profiledClientAnswerDetails, ENRICHED_TOP_K)
                : Collections.emptySet();

        List<ClientCategoryPayload> categories = profiledClientAnswerDetails.getProfiledCategoryClientStatementsList().stream()
                .map(cat -> toCategory(cat, mode, enrichedCategories))
                .toList();

        MinimizedPayload payload = MinimizedPayload.builder()
                .clientName(profiledClientAnswerDetails.getClientName())
                .clientId(profiledClientAnswerDetails.getClientId())
                .submissionId(profiledClientAnswerDetails.getSubmissionId())
                .testName(profiledClientAnswerDetails.getTestName())
                .categories(categories)
                .submissionDate(profiledClientAnswerDetails.getSubmissionDate())
                .build();

        return Mono.just(payload);
    }

    private MinimizedPayload emptyFor(ProfiledClientAnswerDetails profiledClientAnswerDetails) {
        return MinimizedPayload.builder()
                .clientName(profiledClientAnswerDetails.getClientName())
                .clientId(profiledClientAnswerDetails.getClientId())
                .submissionId(profiledClientAnswerDetails.getSubmissionId())
                .testName(profiledClientAnswerDetails.getTestName())
                .categories(List.of())
                .build();
    }

    private ClientCategoryPayload toCategory(ProfiledCategoryClientStatements profiledCategoryClientStatements,
                                             PayloadMode mode,
                                             Set<String> enrichedCats) {
        String categoryId = safe(() -> profiledCategoryClientStatements.getCategory().getCategoryName(), "UNKNOWN_CATEGORY");
        String labelPl = safe(() -> profiledCategoryClientStatements.getCategory().getDescription(), categoryId);

        int limitingTrue = safeInt(profiledCategoryClientStatements.getTotalLimiting());
        int supportingTrue = safeInt(profiledCategoryClientStatements.getTotalSupporting());
        double balance = calcBalance(limitingTrue, supportingTrue);

        int limitPerType = switch (mode) {
            case FULL -> Integer.MAX_VALUE;
            case MINIMAL -> MINIMAL_PER_TYPE;
            case ENRICHED -> (enrichedCats.contains(categoryId) ? ENRICHED_PER_TYPE : MINIMAL_PER_TYPE);
        };

        List<String> limitingEvidence = evidence(profiledCategoryClientStatements, StatementType.LIMITING, limitPerType);
        List<String> supportingEvidence = evidence(profiledCategoryClientStatements, StatementType.SUPPORTING, limitPerType);

        return ClientCategoryPayload.builder()
                .categoryId(categoryId)
                .categoryLabelPl(labelPl)
                .totalLimiting(limitingTrue)
                .totalSupporting(supportingTrue)
                .balanceIndex(balance)
                .limitingEvidence(limitingEvidence)
                .supportingEvidence(supportingEvidence)
                .build();
    }

    private List<String> evidence(ProfiledCategoryClientStatements profiledCategoryClientStatements,
                                  StatementType type,
                                  int limit) {
        if (profiledCategoryClientStatements.getProfiledStatementList() == null) return List.of();

        return profiledCategoryClientStatements.getProfiledStatementList().stream()
                .filter(Objects::nonNull)
                .filter(s -> Boolean.TRUE.equals(s.getStatus()))
                .filter(s -> s.getType() == type)
                .map(ProfiledStatement::getDescription)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .limit(limit)
                .toList();
    }

    private double calcBalance(int limiting, int supporting) {
        int total = limiting + supporting;
        return total == 0 ? 0.5d : ((double) supporting) / total;
    }
//todo do wyjebania moze
    private static int safeInt(Integer v) {
        return v == null ? 0 : v;
    }

    private static <T> T safe(SupplierEx<T> s, T fallback) {
        try {
            return s.get();
        } catch (Exception e) {
            return fallback;
        }
    }

    @FunctionalInterface
    private interface SupplierEx<T> {
        T get() throws Exception;
    }

    private Set<String> pickWorstCategoriesByBalance(ProfiledClientAnswerDetails pca, int k) {
        return pca.getProfiledCategoryClientStatementsList().stream()
                .sorted(Comparator
                        // 1) po rosnącym balanceIndex (im mniejszy, tym "gorzej")
                        .comparingDouble((ProfiledCategoryClientStatements c) ->
                                calcBalance(safeInt(c.getTotalLimiting()), safeInt(c.getTotalSupporting())))
                        // 2) przy remisie – więcej aktywnych (limiting+supporting) ma wyższy priorytet
                        .thenComparingInt((ProfiledCategoryClientStatements c) ->
                                -(safeInt(c.getTotalLimiting()) + safeInt(c.getTotalSupporting()))))
                .limit(Math.max(0, k))
                .map(c -> safe(() -> c.getCategory().getCategoryName(), "UNKNOWN_CATEGORY"))
                .collect(Collectors.toCollection(LinkedHashSet::new)); // ORDER, keeep order
    }

}
