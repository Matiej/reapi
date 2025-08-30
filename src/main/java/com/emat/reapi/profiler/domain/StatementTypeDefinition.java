package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatementTypeDefinition {
    private String key;
    private StatementType statementType;
    private String statementDescription;
}
