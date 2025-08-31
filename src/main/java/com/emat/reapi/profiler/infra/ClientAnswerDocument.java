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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("client_answer")
@CompoundIndex(
        name = "client_category_idx",
        def = "{'clientId': 1, 'statementDocuments.statementCategory': 1}"
)
public class ClientAnswerDocument {
    @Id
    private String id;
    @Indexed(name = "public_id_idx")
    private String publicId = UUID.randomUUID().toString();
    @Indexed(name = "client_id_idx")
    private String clientId;
    private String submissionId;
    private Instant submissionDate;
    private String clientName;
    private String testName;
    private List<ClientStatementDocument> statementDocuments;
    @CreatedDate
    private Instant createdAt;
    @Version
    private Long version;

    public ClientAnswerDocument(String clientId,
                                String submissionId,
                                Instant submissionDate,
                                String clientName,
                                String testName,
                                List<ClientStatementDocument> clientAnswerDocuments) {
        this.clientId = clientId;
        this.submissionId = submissionId;
        this.submissionDate  = submissionDate;
        this.clientName = clientName;
        this.testName = testName;
        this.statementDocuments = clientAnswerDocuments;
    }

    public static ClientAnswerDocument toDocument(ClientAnswer domain) {
        return new ClientAnswerDocument(
                domain.getClientId(),
                domain.getSubmissionId(),
                domain.getSubmissionDate(),
                domain.getName(),
                domain.getTestName(),
                ClientStatementDocument.toDocumentList(domain.getClientStatementList()));
    }

    public ClientAnswer toDomain() {
        return new ClientAnswer(
                clientId,
                publicId,
                submissionId,
                submissionDate,
                clientName,
                testName,
                createdAt,
                statementDocuments.stream()
                        .map(ClientStatementDocument::toDomain)
                        .toList());
    }
}
