package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.InsightReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("profiler_insight_report")
@CompoundIndexes({
        @CompoundIndex(name = "submission_created_desc", def = "{'submissionId': 1, 'createdAt': -1}"),
        @CompoundIndex(name = "client_created_desc",    def = "{'clientId': 1, 'createdAt': -1}")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsightReportDocument {
    @Id
    private String id;
    private String clientName;
    private String submissionId;
    private String clientId;
    private String testName;
    private String aiModel;
    private String schemaName;
    private String schemaVersion;
    private String reportJson;
    private Instant createdAt;
    private InsightReportStructuredAiDocument insightReportStructuredAiDocument;

    public static InsightReportDocument from(InsightReport report) {
        var doc = new InsightReportDocument();
        doc.setClientName(report.getClientName());
        doc.setSubmissionId(report.getSubmissionId());
        doc.setClientId(report.getClientId());
        doc.setTestName(report.getTestName());
        doc.setAiModel(report.getModel());
        doc.setSchemaName(report.getSchemaName());
        doc.setSchemaVersion(report.getSchemaVersion());
        doc.setReportJson(report.getRawJson());
        doc.setInsightReportStructuredAiDocument(InsightReportStructuredAiDocument.from(report.getInsightReportStructuredAiResponse()));
        doc.setCreatedAt(report.getCreatedAt() != null ? report.getCreatedAt() : Instant.now());
        return doc;
    }

    public InsightReport toDomain() {
        return InsightReport.builder()
                .submissionId(submissionId)
                .clientId(clientId)
                .clientName(clientName)
                .testName(testName)
                .model(aiModel)
                .schemaName(schemaName)
                .schemaVersion(schemaVersion)
                .createdAt(createdAt)
                .rawJson(reportJson)
                .insightReportStructuredAiResponse(insightReportStructuredAiDocument.toDomain())
                .build();
    }

}
