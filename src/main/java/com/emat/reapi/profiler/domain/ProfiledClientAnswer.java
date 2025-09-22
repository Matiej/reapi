package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class ProfiledClientAnswer {
    private String clientName;
    private String clientId;
    private String submissionId;
    private Instant submissionDate;
    private String testName;
    private List<ProfiledCategoryClientStatements> profiledCategoryClientStatementsList;
}
