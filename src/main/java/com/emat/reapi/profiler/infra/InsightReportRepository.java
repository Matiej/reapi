package com.emat.reapi.profiler.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface InsightReportRepository extends ReactiveMongoRepository<InsightReportDocument, String> {
    Flux<InsightReportDocument> findAllBySubmissionId(String submissionId);
}
