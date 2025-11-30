package com.emat.reapi.clienttest.infra;


import com.emat.reapi.clienttest.domain.ClientTestSubmission;
import com.emat.reapi.statement.domain.StatementDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = ClientTestDocument.COLLECTION_NAME)
@TypeAlias(value = ClientTestDocument.COLLECTION_NAME)
public class ClientTestDocument {
    public final static String COLLECTION_NAME = "client_tests";

    @Id
    private String id;
    @Indexed(name = "testSubmissionPublicId_idx", background = true)
    private String testSubmissionPublicId;
    private String clientId;
    private String clientName;
    private String clientEmail;
    @Indexed(name = "testId_idx", background = true)
    private String testId;
    private String testName;
    @Indexed(name = "submissionId_idx", unique = true, background = true)
    private String submissionId;
    private Instant submissionDate;
    private String publicToken;
    private List<ClientTestAnswerDocument> answers;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    private Long version;

    public ClientTestSubmission toDomain() {
        return new ClientTestSubmission(
                testSubmissionPublicId,
                clientId,
                clientName,
                clientEmail,
                submissionId,
                submissionDate,
                testId,
                testName,
                publicToken,
                answers.stream().map(ClientTestAnswerDocument::toDomain).toList()
        );
    }

    public static ClientTestDocument fromDomain(
            ClientTestSubmission submission,
            List<StatementDefinition> statementDefinitions
    ) {
        var definitionByKey = statementDefinitions.stream()
                .collect(Collectors.toMap(
                        StatementDefinition::getStatementKey,
                        d -> d
                ));

        var answerDocs = submission.getClientTestAnswerList().stream()
                .map(a -> ClientTestAnswerDocument.fromDomain(a, definitionByKey.get(a.questionKey())))
                .toList();

        ClientTestDocument clientTestDocument = new ClientTestDocument();
        clientTestDocument.setId(submission.getId());
        clientTestDocument.setTestSubmissionPublicId(submission.getTestSubmissionPublicId());
        clientTestDocument.setClientId(submission.getClientId());
        clientTestDocument.setClientName(submission.getClientName());
        clientTestDocument.setClientEmail(submission.getClientEmail());
        clientTestDocument.setTestId(submission.getTestId());
        clientTestDocument.setTestName(submission.getTestName());
        clientTestDocument.setSubmissionId(submission.getSubmissionId());
        clientTestDocument.setSubmissionDate(submission.getSubmissionDate());
        clientTestDocument.setPublicToken(submission.getPublicToken());
        clientTestDocument.setAnswers(answerDocs);
        return clientTestDocument;
    }
}
