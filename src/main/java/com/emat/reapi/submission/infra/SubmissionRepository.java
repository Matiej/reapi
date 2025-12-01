package com.emat.reapi.submission.infra;

import com.emat.reapi.submission.domain.SubmissionStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends ReactiveMongoRepository<SubmissionDocument, String> {
    Mono<SubmissionDocument> findBySubmissionId(String submissionId);
    Mono<Void> deleteBySubmissionId(String submissionId);
    Flux<SubmissionDocument> findAllByOrderByCreatedAtDesc();
    Mono<Boolean> existsByTestId(String testId);
    Flux<SubmissionDocument> findAllByTestId(String testId);
    Mono<SubmissionDocument> findByPublicTokenAndStatus(String publicToken, SubmissionStatus status);

    Mono<SubmissionDocument> findByPublicTokenAndStatusAndExpireAtAfter(String publicToken, SubmissionStatus status, Instant now);
}
