package com.emat.reapi.profileanalysis.infra;

import com.emat.reapi.profileanalysis.domain.CategoryInsight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInsightDocument {
    @Field("categoryId")
    private String categoryId;

    @Field("categoryLabelPl")
    private String categoryLabelPl;

    @Field("strengthsPl")
    private List<String> strengthsPl;

    @Field("risksPl")
    private List<String> risksPl;
    @Field("contradictionsPl")
    private List<String> contradictionsPl;
    @Field("recommendedInterventions")
    private List<RecommendedInterventionDocument> recommendedInterventions;

    public static CategoryInsightDocument from(CategoryInsight categoryInsight) {
        if (categoryInsight == null) return null;
        var doc = new CategoryInsightDocument();
        doc.setCategoryId(categoryInsight.getCategoryId());
        doc.setCategoryLabelPl(categoryInsight.getCategoryLabelPl());
        doc.setStrengthsPl(categoryInsight.getStrengthsPl());
        doc.setRisksPl(categoryInsight.getRisksPl());
        doc.setContradictionsPl(categoryInsight.getContradictionsPl());

        if (categoryInsight.getRecommendedInterventions() != null) {
            doc.setRecommendedInterventions(
                    categoryInsight.getRecommendedInterventions().stream()
                            .map(RecommendedInterventionDocument::from)
                            .collect(Collectors.toList())
            );
        }
        return doc;
    }

    public CategoryInsight toDomain() {
        return CategoryInsight.builder()
                .categoryId(categoryId)
                .categoryLabelPl(categoryLabelPl)
                .strengthsPl(strengthsPl)
                .risksPl(risksPl)
                .contradictionsPl(contradictionsPl)
                .recommendedInterventions(recommendedInterventions != null
                        ? recommendedInterventions
                        .stream()
                        .map(RecommendedInterventionDocument::toDomain)
                        .toList()
                        : null
                )
                .build();
    }
}
