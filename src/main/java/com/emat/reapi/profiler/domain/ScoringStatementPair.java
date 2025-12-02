package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoringStatementPair {
    private String statementKey;
    private String limitingDescription;
    private String supportingDescription;
    private int scoring;
}
