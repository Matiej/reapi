package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.ClientAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("client_answer")
@CompoundIndex(
        name = "client_category_idx",
        def = "{'clientId': 1, 'answeredStatementList.statementCategory': 1}"
)
public class ClientAnswerDocument {
    @Id
    private String id;
    @Indexed(name = "client_id_idx")
    private String clientId;
    private String submissionId;
    private String name;
    private String testName;
    private List<ClientStatementDocument> statementDocuments;
    @CreatedDate
    private Instant createdAt;
    @Version
    private Long version;


    public ClientAnswerDocument(String clientId, String submissionId, String name, String testName, List<ClientStatementDocument> clientAnswerDocuments) {
        this.clientId = clientId;
        this.submissionId = submissionId;
        this.name = name;
        this.testName = testName;
        this.statementDocuments = clientAnswerDocuments;
    }

    public static ClientAnswerDocument toDocument(ClientAnswer domain) {
        return new ClientAnswerDocument(
                domain.getClientId(),
                domain.getSubmissionId(),
                domain.getName(),
                domain.getTestName(),
                ClientStatementDocument.toDocumentList(domain.getClientStatementList()));
    }

    public ClientAnswer toDomain() {
        return new ClientAnswer(
                clientId,
                submissionId,
                name,
                testName,
                statementDocuments.stream()
                        .map(ClientStatementDocument::toDomain)
                        .toList());
    }
}
