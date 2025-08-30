package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.ClientStatement;
import com.emat.reapi.profiler.domain.Statement;
import com.emat.reapi.profiler.domain.StatementCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientStatementDocument {
    private String statementId;
    private String key;
    private List<Statement> statements;
    private StatementCategory statementCategory;

    public static List<ClientStatementDocument> toDocumentList(List<ClientStatement> clientStatementList) {
        return clientStatementList.stream().map(ClientStatementDocument::toDocument).toList();
    }

    public static ClientStatementDocument toDocument(ClientStatement domain) {
        return new ClientStatementDocument(
                domain.getStatementId(),
                domain.getKey(),
                domain.getStatementList(),
                domain.getStatementCategory());
    }

    public ClientStatement toDomain() {
        return new ClientStatement(
                statementId,
                key,
                statements,
                statementCategory
        );
    }

}
