package com.emat.reapi.profileanalysis.infra;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface InsightReportRepository extends ReactiveMongoRepository<InsightReportDocument, String> {
    Flux<InsightReportDocument> findAllBySubmissionId(String submissionId);
    Flux<InsightReportDocument> findBySubmissionIdOrderByCreatedAtDesc(String submissionId);

    @Aggregation(pipeline = {
            "{ $group: { _id: '$submissionId' } }",
            "{ $project: { _id: 0, submissionId: '$_id' } }"
    })
    Flux<String> distinctSubmissionIdsAll();
}
