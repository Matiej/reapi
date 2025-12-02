package com.emat.reapi.profileanalysis.domain;

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
