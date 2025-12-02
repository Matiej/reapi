package com.emat.reapi.profileanalysis.infra;

import com.emat.reapi.profileanalysis.domain.InsightReportStructuredAiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsightReportStructuredAiDocument {

    @Field("clientSummary")
    private ClientSummaryDocument clientSummary;

    @Field("categoryInsights")
    private List<CategoryInsightDocument> categoryInsights;

    @Field("nextSteps")
    private List<String> nextSteps;

    public static InsightReportStructuredAiDocument from(InsightReportStructuredAiResponse src) {
        if (src == null) return null;
        var doc = new InsightReportStructuredAiDocument();

        if (src.getClientSummary() != null) {
            doc.setClientSummary(ClientSummaryDocument.from(src.getClientSummary()));
        }

        if (src.getCategoryInsights() != null) {
            doc.setCategoryInsights(
                    src.getCategoryInsights().stream()
                            .map(CategoryInsightDocument::from)
                            .collect(Collectors.toList())
            );
        }

        doc.setNextSteps(src.getNextSteps());
        return doc;
    }

    public InsightReportStructuredAiResponse toDomain() {
        return InsightReportStructuredAiResponse.builder()
                .clientSummary(clientSummary != null ? clientSummary.toDomain() : null)
                .categoryInsights(categoryInsights != null
                        ? categoryInsights
                        .stream()
                        .map(CategoryInsightDocument::toDomain)
                        .toList()
                        : null
                )
                .nextSteps(nextSteps)
                .build();
    }
}
