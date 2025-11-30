package com.emat.reapi.clienttest.infra;

import com.emat.reapi.clienttest.domain.ClientTestAnswer;
import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.emat.reapi.statement.domain.StatementType.LIMITING;
import static com.emat.reapi.statement.domain.StatementType.SUPPORTING;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientTestAnswerDocument {
    private String questionKey;
    private StatementCategory category;
    private String limitingDescription;
    private String supportingDescription;
    private int scoring;

    public static ClientTestAnswerDocument fromDomain(
            ClientTestAnswer domain,
            StatementDefinition definition
    ) {
        var limiting = definition.getStatementTypeDefinitions().stream()
                .filter(d -> d.getStatementType() == LIMITING)
                .findFirst()
                .orElseThrow();
        var supporting = definition.getStatementTypeDefinitions().stream()
                .filter(d -> d.getStatementType()  == SUPPORTING)
                .findFirst()
                .orElseThrow();

        return new ClientTestAnswerDocument(
                domain.questionKey(),
                definition.getCategory(),
                limiting.getStatementDescription(),
                supporting.getStatementDescription(),
                domain.scoring()
        );
    }

    public ClientTestAnswer toDomain() {
        return new ClientTestAnswer(questionKey, scoring);
    }
}
