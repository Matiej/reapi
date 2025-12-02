package com.emat.reapi.profileanalysis.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder
public class MinimizedPayload {
    String clientName;
    String clientId;
    String submissionId;
    String testName;
    Instant submissionDate;
    List<ClientCategoryPayload> categories;
}
