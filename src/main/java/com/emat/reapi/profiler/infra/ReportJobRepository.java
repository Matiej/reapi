package com.emat.reapi.profiler.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReportJobRepository extends ReactiveMongoRepository<ReportJobDocument, String> {
    Mono<ReportJobDocument> findBySubmissionId(String submissionId);
}
