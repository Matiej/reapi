package com.emat.reapi.statement.domain;

import com.emat.reapi.api.dto.AnsweredStatementDto;
import com.emat.reapi.api.dto.AnsweredStatementResponse;
import com.emat.reapi.api.dto.StatementDto;
import com.emat.reapi.api.dto.StatementResponse;
import com.emat.reapi.statement.infra.StatementDefinitionsDictionary;

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

    public static AnsweredStatementResponse toAnsweredStatementResponse(ClientStatement clientStatement) {
        return new AnsweredStatementResponse(
                clientStatement.getStatementId(),
                clientStatement.getKey(),
                clientStatement.getStatementCategory().getPlName(),
                clientStatement.getStatementList().stream().map(StatementMapper::toStatementResponseDto).toList()
        );
    }

    private static StatementResponse toStatementResponseDto(Statement statement) {
        return new StatementResponse(
                statement.getStatementKey(),
                statement.getStatementDescription(),
                statement.getStatementStatus(),
                statement.getStatementType().getPlDescription()
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
