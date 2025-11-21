package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = InsightReportDocument.COLLECTION_NAME)
@TypeAlias(value = InsightReportDocument.COLLECTION_NAME)
@CompoundIndexes({
        @CompoundIndex(name = "submission_created_desc", def = "{'submissionId': 1, 'createdAt': -1}"),
        @CompoundIndex(name = "client_created_desc",    def = "{'clientId': 1, 'createdAt': -1}")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsightReportDocument {

    public static final String COLLECTION_NAME = "profiler_insight_report";

    @Id
    private String id;
    private String clientName;
    private String submissionId;
    private String clientId;
    private String testName;
    private String aiModel;
    private PayloadMode payloadMode;
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
        doc.setPayloadMode(report.getPayloadMode());
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
                .payloadMode(payloadMode)
                .schemaName(schemaName)
                .schemaVersion(schemaVersion)
                .createdAt(createdAt)
                .rawJson(reportJson)
                .insightReportStructuredAiResponse(insightReportStructuredAiDocument.toDomain())
                .build();
    }

}
