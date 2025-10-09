package com.emat.reapi.profiler.domain.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedIntervention {
    private String type;
    private String titlePl;
    private String whyPl;
    private String howPl;
}
