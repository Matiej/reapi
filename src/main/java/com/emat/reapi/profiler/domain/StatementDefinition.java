package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatementDefinition {
    private String statementId;
    private String leftStatement;
    private String rightStatement;
    private String category;
}
