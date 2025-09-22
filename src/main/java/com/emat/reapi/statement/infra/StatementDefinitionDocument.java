package com.emat.reapi.statement.infra;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.domain.StatementTypeDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("statement_definitions")
public class StatementDefinitionDocument {
    @Id
    private String id;
    private String statementId;
    private String statementKey;
    private StatementCategory category;
    private List<StatementTypeDefinition> statementTypeDefinitions;

    public StatementDefinitionDocument(String statementId, String statementKey, StatementCategory category, List<StatementTypeDefinition> statementTypeDefinitions) {
        this.statementId = statementId;
        this.statementKey = statementKey;
        this.category = category;
        this.statementTypeDefinitions = statementTypeDefinitions;
    }

    public StatementDefinition toDomain() {
        return new StatementDefinition(statementId,  category, statementKey,statementTypeDefinitions);
    }

    public static StatementDefinitionDocument toDocument(StatementDefinition domain) {
        return new StatementDefinitionDocument(
                domain.getStatementId(),
                domain.getStatementKey(),
                domain.getCategory(),
                domain.getStatementTypeDefinitions()
        );
    }
}
