package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.RecommendedIntervention;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedInterventionDocument {
    @Field("type")
    private String type;

    @Field("titlePl")
    private String titlePl;

    @Field("whyPl")
    private String whyPl;

    @Field("howPl")
    private String howPl;

    public static RecommendedInterventionDocument from(RecommendedIntervention recommendedIntervention) {
        if (recommendedIntervention == null) return null;
        var doc = new RecommendedInterventionDocument();
        doc.setType(recommendedIntervention.getType());
        doc.setTitlePl(recommendedIntervention.getTitlePl());
        doc.setWhyPl(recommendedIntervention.getWhyPl());
        doc.setHowPl(recommendedIntervention.getHowPl());
        return doc;
    }

    public RecommendedIntervention toDomain() {
        return RecommendedIntervention.builder()
                .type(type)
                .titlePl(titlePl)
                .whyPl(whyPl)
                .howPl(howPl)
                .build();
    }

}
