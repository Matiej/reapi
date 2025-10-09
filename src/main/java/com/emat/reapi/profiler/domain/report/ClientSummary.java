package com.emat.reapi.profiler.domain.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class ClientSummary {
    private List<String> keyThemes;
    private List<DominantCategory> dominantCategories;
    private String overallNarrativePl;
}
