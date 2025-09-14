package com.emat.reapi.statement.infra;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementCategoryDefinition;

import java.util.Map;

public class StatementCategoryDefinitionDictionary {
    private StatementCategoryDefinitionDictionary() {

    }

    public static final Map<StatementCategory, StatementCategoryDefinition> ALL = Map.of(
            StatementCategory.SCARCITY_GUARDIAN, new StatementCategoryDefinition(
                    StatementCategory.SCARCITY_GUARDIAN,
                    StatementCategory.SCARCITY_GUARDIAN.getPlDescription(),
                    ""),
            StatementCategory.SELF_SUFFICIENT_SHIELD, new StatementCategoryDefinition(
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    StatementCategory.SELF_SUFFICIENT_SHIELD.getPlDescription(),
                    ""),
            StatementCategory.FROZEN_VISIONARY, new StatementCategoryDefinition(
                    StatementCategory.FROZEN_VISIONARY,
                    StatementCategory.FROZEN_VISIONARY.getPlDescription(),
                    ""),
            StatementCategory.LOYAL_HEIRESS, new StatementCategoryDefinition(
                    StatementCategory.LOYAL_HEIRESS,
                    StatementCategory.LOYAL_HEIRESS.getPlDescription(),
                    ""),
            StatementCategory.WITHDRAWN_LEADER, new StatementCategoryDefinition(
                    StatementCategory.WITHDRAWN_LEADER,
                    StatementCategory.WITHDRAWN_LEADER.getPlDescription(),
                    ""),
            StatementCategory.OVERWORKED_PERFECTIONIST, new StatementCategoryDefinition(
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    StatementCategory.OVERWORKED_PERFECTIONIST.getPlDescription(),
                    ""),
            StatementCategory.BLOCKED_IN_RECEIVING, new StatementCategoryDefinition(
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    StatementCategory.BLOCKED_IN_RECEIVING.getPlDescription(),
                    ""),
            StatementCategory.MODESTY_IDEALIST, new StatementCategoryDefinition(
                    StatementCategory.MODESTY_IDEALIST,
                    StatementCategory.MODESTY_IDEALIST.getPlDescription(),
                    "")
    );
}
