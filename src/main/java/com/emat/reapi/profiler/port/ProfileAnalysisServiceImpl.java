package com.emat.reapi.profiler.port;

import com.emat.reapi.ai.port.AiInsightAnalyzerService;
import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.domain.report.InsightReportAiResponse;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.infra.InsightReportDocument;
import com.emat.reapi.profiler.infra.InsightReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProfileAnalysisServiceImpl implements ProfileAnalysisService {
    private final ProfiledService profiledService;
    private final MinimizerService minimizerService;
    private final AiInsightAnalyzerService aiAnalyzer;
    private final InsightReportRepository reportRepository;

    @Override
    public Mono<InsightReportAiResponse> analyzeSubmission(String submissionId, boolean force, PayloadMode mode, int retry) {
        Mono<InsightReport> existing = reportRepository.findBySubmissionId(submissionId)
                .map(InsightReportDocument::toDomain);

        return profiledService.getClientProfiledStatement(submissionId)
                .flatMap(pca -> minimizerService.minimize(pca, mode))
                .flatMap(aiAnalyzer::analyze)
                .flatMap(resposne -> {
                    var report = InsightReport.of(
                            submissionId,
                            resposne.getClientId(),
                            resposne.getTestName(),
                            resposne.getModel(),
                            resposne.getSchemaName(),
                            resposne.getSchemaVersion(),
                            resposne.getRawJson(),
                            resposne.getDate(),
                            resposne.getInsightReportStructuredAiResponse()
                    );
                    return reportRepository.save(InsightReportDocument.from(report))
                            .thenReturn(resposne);
                });

    }

    @Override
    public Mono<InsightReport> getAnalysis(String submissionId) {
        return reportRepository.findBySubmissionId(submissionId)
                .map(InsightReportDocument::toDomain);
    }
}

