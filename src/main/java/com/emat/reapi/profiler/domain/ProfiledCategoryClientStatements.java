package com.emat.reapi.profiler.domain;

import com.emat.reapi.statement.domain.ClientStatement;
import com.emat.reapi.statement.domain.Statement;
import com.emat.reapi.statement.domain.StatementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfiledCategoryClientStatements {
    private ProfileCategory category;
    private int totalLimiting;
    private int totalSupporting;
    private List<ProfiledStatement> profiledStatementList = new ArrayList<>();

    public static int countTypes(StatementType type, List<Statement> statementList) {
        return statementList.stream().filter(p -> p.getStatementType().equals(type) && p.getStatementStatus()).toList().size();
    }

}
