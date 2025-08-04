package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.StatementDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("statement_definitions")
public class StatementDefinitionDocument {
    @Id
    private String id;
    private String statementId;
    private String leftStatement;
    private String rightStatement;
    private String category;

    public StatementDefinitionDocument(String statementId, String leftStatement, String rightStatement, String category) {
        this.statementId = statementId;
        this.leftStatement = leftStatement;
        this.rightStatement = rightStatement;
        this.category = category;
    }

    public StatementDefinition toDomain() {
        return new StatementDefinition(statementId, leftStatement, rightStatement, category);
    }

    public static StatementDefinitionDocument toDocument(StatementDefinition domain) {
        return new StatementDefinitionDocument(
                domain.getStatementId(),
                domain.getLeftStatement(),
                domain.getRightStatement(),
                domain.getCategory()
        );
    }



}
