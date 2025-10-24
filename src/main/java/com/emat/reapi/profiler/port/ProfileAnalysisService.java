package com.emat.reapi.profiler.port;

import com.emat.reapi.api.dto.InsightReportDto.ReportJobStatusDto;
import com.emat.reapi.profiler.domain.report.InsightReport;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.domain.reportjob.ReportJob;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfileAnalysisService {
    Mono<ReportJobStatusDto> getLatestAnalysisStatus(String submissionId);

    Mono<List<InsightReport>> getAnalysis(String submissionId);
    Mono<Void> saveReport(InsightReport insightReport);

    Mono<ReportJob> enqueueStatementToAnalyze(String submissionId, PayloadMode mode, boolean force, int retry);
}
