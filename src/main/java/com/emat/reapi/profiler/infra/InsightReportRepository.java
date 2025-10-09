package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.InsightReport;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InsightReportRepository extends ReactiveMongoRepository<InsightReportDocument, String> {
    Mono<InsightReportDocument> findBySubmissionId(String submissionId);
}
