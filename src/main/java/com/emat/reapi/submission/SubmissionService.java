package com.emat.reapi.submission;

import com.emat.reapi.api.dto.SubmissionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubmissionService {
    Mono<Submission> findBySubmissionId(String submissionId);
    Mono<Submission> createSubmission(SubmissionDto request);
    Flux<Submission> findAllByOrderByCreatedAtDesc();
    Mono<Void> closeSubmission();
}
