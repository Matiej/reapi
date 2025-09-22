package com.emat.reapi.statement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientStatement {
    private String statementId;
    private String key;
    private List<Statement> statementList;
    private StatementCategory statementCategory;
}
