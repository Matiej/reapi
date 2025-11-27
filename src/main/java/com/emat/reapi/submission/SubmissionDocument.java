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
    @Indexed(name = "orderId_idx", unique = true, background = true)
    private String orderId;
    private String clientId;
    private String clientName;
    @Indexed(name = "testId_idx", background = true)
    private String testId;
    private String clientEmail;
    private SubmissionStatus status;
    private int durationDays;
    @Indexed(name = "publicToken_idx", unique = true, background = true)
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
                this.clientEmail,
                this.orderId,
                this.testId,
                this.status,
                this.durationDays,
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
        doc.setClientEmail(domain.clientEmail());
        doc.setOrderId(domain.orderId());
        doc.setTestId(domain.testId());
        doc.setStatus(domain.status());
        doc.setDurationDays(domain.durationDays());
        doc.setExpireAt(domain.expireAt());
        doc.setPublicToken(domain.publicToken());
        doc.setCreatedAt(domain.createdAt());
        return doc;
    }
}


