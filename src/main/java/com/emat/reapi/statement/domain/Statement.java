package com.emat.reapi.statement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statement {
    private String statementKey;
    private String statementDescription;
    private StatementType statementType;
    private Boolean statementStatus;
}
