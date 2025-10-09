package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ClientCategoryPayload {
    String categoryId;
    String categoryLabelPl;
    int totalLimiting;
    int totalSupporting;
    double balanceIndex;
    List<String> limitingEvidence;
    List<String> supportingEvidence;
}
