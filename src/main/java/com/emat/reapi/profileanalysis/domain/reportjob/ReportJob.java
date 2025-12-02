package com.emat.reapi.profileanalysis.domain.reportjob;

import com.emat.reapi.profileanalysis.domain.PayloadMode;
import com.emat.reapi.profileanalysis.infra.ReportJobDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportJob {
    private String id;
    private String submissionId;
    private ReportJobStatus status;
    private PayloadMode mode;
    private String error;
    private Instant expireAt;
    private Instant createdAt;
    private Instant updatedAt;

    public static ReportJob fromDocument(ReportJobDocument reportJobDocument) {
        return new ReportJob(
                reportJobDocument.getId(),
                reportJobDocument.getSubmissionId(),
                reportJobDocument.getStatus(),
                reportJobDocument.getMode(),
                reportJobDocument.getError(),
                reportJobDocument.getExpireAt(),
                reportJobDocument.getCreatedAt(),
                reportJobDocument.getUpdatedAt()
        );
    }

    public ReportJobDocument toDocument() {
        return new ReportJobDocument(
                id,
                submissionId,
                status,
                mode,
                error,
                expireAt,
                createdAt,
                updatedAt
        );
    }
}
