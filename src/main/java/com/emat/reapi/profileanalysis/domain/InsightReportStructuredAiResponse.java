package com.emat.reapi.profileanalysis.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsightReportStructuredAiResponse {
    @JsonProperty("clientSummary")
    private ClientSummary clientSummary;

    @JsonProperty("categoryInsights")
    private List<CategoryInsight> categoryInsights;

    @JsonProperty("nextSteps")
    private List<String> nextSteps;

}
