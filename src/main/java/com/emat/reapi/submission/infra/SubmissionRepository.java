package com.emat.reapi.submission;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubmissionRepository extends ReactiveMongoRepository<SubmissionDocument, String> {
    Mono<SubmissionDocument> findBySubmissionId(String submissionId);
    Mono<Void> deleteBySubmissionId(String submissionId);
    Flux<SubmissionDocument> findAllByOrderByCreatedAtDesc();
    Mono<Boolean> existsByTestId(String testId);
    Flux<SubmissionDocument> findAllByTestId(String testId);
}
