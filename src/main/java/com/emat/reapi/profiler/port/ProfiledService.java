package com.emat.reapi.profiler.port;

import com.emat.reapi.api.dto.InsightReportDto.ReportJobStatusDto;
import com.emat.reapi.profiler.domain.ProfiledClientAnswerDetails;
import com.emat.reapi.profiler.domain.ProfiledClientAnswerShort;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.domain.reportjob.ReportJob;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfiledService {

    Mono<ProfiledClientAnswerDetails> getClientProfiledStatements(String clientId);
    Mono<ProfiledClientAnswerDetails> getClientProfiledStatement(String submissionId);
    Mono<List<ProfiledClientAnswerShort>> getProfiledShort(Sort sort);

    Mono<ReportJob> enqueueStatementToAnalyze(String submissionId, PayloadMode mode, boolean force, int retry);
    Mono<ReportJobStatusDto> getLatestAnalysisStatus(String submissionId);




}
