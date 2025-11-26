package com.emat.reapi.submission;

import com.emat.reapi.api.dto.SubmissionDto;
import com.emat.reapi.api.dto.SubmissionUpdateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubmissionService {
    Mono<Submission> findBySubmissionId(String submissionId);
    Mono<Submission> createSubmission(SubmissionDto request);
    Mono<Submission> updateSubmission(SubmissionUpdateDto request, String submissionId);
    Flux<Submission> findAllByOrderByCreatedAtDesc();
    Mono<Void> deleteSubmission(String submissionId);
    Mono<Submission> closeSubmission(String submissionId);
}
