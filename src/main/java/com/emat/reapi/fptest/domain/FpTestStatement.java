package com.emat.reapi.fptest.domain;

import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.domain.StatementType;
import com.emat.reapi.statement.domain.StatementTypeDefinition;

public record FpTestStatement(
        String statementKey,
        String statementsDescription,
        String statementsCategory
) {

    public static FpTestStatement formStatementDefinition(StatementDefinition statementDefinition) {
        var limitingDesc = descriptionFor(statementDefinition, StatementType.LIMITING);
        var supportingDesc = descriptionFor(statementDefinition, StatementType.SUPPORTING);
        var desc = (limitingDesc + "-" + supportingDesc).trim();
        return new FpTestStatement(
                statementDefinition.getStatementKey(),
                desc,
                statementDefinition.getCategory().getPlName()
        );
    }

    private static String descriptionFor(StatementDefinition definition, StatementType type) {
        return definition.getStatementTypeDefinitions()
                .stream()
                .filter(def -> def.getStatementType() == type)
                .map(StatementTypeDefinition::getStatementDescription)
                .findFirst()
                .orElse("");
    }

}
