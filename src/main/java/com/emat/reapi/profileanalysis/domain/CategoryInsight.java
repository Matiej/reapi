package com.emat.reapi.profileanalysis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInsight {
    private String categoryId;
    private String categoryLabelPl;
    private List<String> strengthsPl;
    private List<String> risksPl;
    /** Opcjonalne w schema – może być null albo pusta lista. */
    private List<String> contradictionsPl;
    private List<RecommendedIntervention> recommendedInterventions;
}
