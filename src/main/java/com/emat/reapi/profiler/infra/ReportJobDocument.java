package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.domain.reportjob.ReportJobStatus;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("reportJobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportJobDocument {
    @Id
    private String id;
    @Indexed(name = "submissionId_idx", unique = true)
    private String submissionId;
    private ReportJobStatus status;
    private String insightReportDocumentId;
    private PayloadMode mode;
    private String error;
    @Indexed(name = "expireAt_ttl_idx", expireAfter = "PT0S")
    private Instant expireAt;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public ReportJobDocument(String id, String submissionId, ReportJobStatus status, String insightReportDocumentId,
                             PayloadMode mode, String error, Instant expireAt) {
        this.id = id;
        this.submissionId = submissionId;
        this.status = status;
        this.insightReportDocumentId = insightReportDocumentId;
        this.mode = mode;
        this.error = error;
        this.expireAt = expireAt;
    }
}
