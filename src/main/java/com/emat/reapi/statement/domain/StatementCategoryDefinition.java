package com.emat.reapi.statement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatementCategoryDefinition {
    private StatementCategory statementCategory;
    private String categoryName;
    private String longDescription;

}
