package com.emat.reapi.statement.infra.dictionary;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementCategoryDefinition;

import java.util.Map;

public class StatementCategoryDefinitionDictionary {
    private StatementCategoryDefinitionDictionary() {

    }

    public static final Map<StatementCategory, StatementCategoryDefinition> ALL = Map.of(
            StatementCategory.SCARCITY_GUARDIAN, new StatementCategoryDefinition(
                    StatementCategory.SCARCITY_GUARDIAN,
                    StatementCategory.SCARCITY_GUARDIAN.getPlName(),
                    ""),
            StatementCategory.SELF_SUFFICIENT_SHIELD, new StatementCategoryDefinition(
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    StatementCategory.SELF_SUFFICIENT_SHIELD.getPlName(),
                    ""),
            StatementCategory.FROZEN_VISIONARY, new StatementCategoryDefinition(
                    StatementCategory.FROZEN_VISIONARY,
                    StatementCategory.FROZEN_VISIONARY.getPlName(),
                    ""),
            StatementCategory.LOYAL_HEIRESS, new StatementCategoryDefinition(
                    StatementCategory.LOYAL_HEIRESS,
                    StatementCategory.LOYAL_HEIRESS.getPlName(),
                    ""),
            StatementCategory.WITHDRAWN_LEADER, new StatementCategoryDefinition(
                    StatementCategory.WITHDRAWN_LEADER,
                    StatementCategory.WITHDRAWN_LEADER.getPlName(),
                    ""),
            StatementCategory.OVERWORKED_PERFECTIONIST, new StatementCategoryDefinition(
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    StatementCategory.OVERWORKED_PERFECTIONIST.getPlName(),
                    ""),
            StatementCategory.BLOCKED_IN_RECEIVING, new StatementCategoryDefinition(
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    StatementCategory.BLOCKED_IN_RECEIVING.getPlName(),
                    ""),
            StatementCategory.MODESTY_IDEALIST, new StatementCategoryDefinition(
                    StatementCategory.MODESTY_IDEALIST,
                    StatementCategory.MODESTY_IDEALIST.getPlName(),
                    "")
    );
}
