package com.emat.reapi.submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(SubmissionDocument.COLLECTION_NAME)
@TypeAlias(SubmissionDocument.COLLECTION_NAME)
public class SubmissionDocument {

    public final static String COLLECTION_NAME = "submissions";

    @Id
    private String id;
    @Indexed(name = "submissionId_idx", background = true)
    private String submissionId;
    private String clientId;
    private String clientName;
    private String testName;
    private SubmissionStatus status;
    private int durationMinutes;
    private String publicToken;
    private Instant expireAt;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    private Long version;

    public Submission toDomain() {
        return new Submission(
                this.id,
                this.submissionId,
                this.clientId,
                this.clientName,
                this.testName,
                this.status,
                this.durationMinutes,
                this.publicToken,
                this.expireAt,
                this.createdAt
        );
    }

    public static SubmissionDocument toDocument(Submission domain) {
        SubmissionDocument doc = new SubmissionDocument();
        doc.setId(domain.id());
        doc.setSubmissionId(domain.submissionId());
        doc.setClientId(domain.clientId());
        doc.setClientName(domain.clientName());
        doc.setTestName(domain.testName());
        doc.setStatus(domain.status());
        doc.setDurationMinutes(domain.durationMinutes());
        doc.setExpireAt(domain.expireAt());
        doc.setPublicToken(domain.publicToken());
        doc.setCreatedAt(domain.createdAt());
        return doc;
    }
}


