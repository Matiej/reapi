package com.emat.reapi.profiler.domain;

import com.emat.reapi.api.dto.AnsweredStatementDto;
import com.emat.reapi.api.dto.StatementDto;
import com.emat.reapi.profiler.infra.StatementDefinitionsDictionary;

import java.util.List;

public final class StatementMapper {
    private StatementMapper() {
    }

    public static ClientStatement toDomain(AnsweredStatementDto dto) {
        if (dto == null) return null;
        var def = StatementDefinitionsDictionary.requireById(dto.getStatementId());
        return new ClientStatement(
                def.getStatementId(),
                def.getStatementKey(),
                toStatements(def.getStatementTypeDefinitions(), dto.getStatementDtoList()),
                def.getCategory()
        );
    }

    private static List<Statement> toStatements(List<StatementTypeDefinition> statementDefinitions,
                                                List<StatementDto> dto) {
        return statementDefinitions.stream()
                .map(def -> new Statement(
                        def.getKey(),
                        def.getStatementDescription(),
                        def.getStatementType(),
                        dto.stream().//TODO refactor this shit.
                                filter(p -> p.statementKey().equalsIgnoreCase(def.getKey()))
                                .map(StatementDto::status)
                                .findAny()
                                .orElse(Boolean.FALSE)

                )).toList();
    }

}
