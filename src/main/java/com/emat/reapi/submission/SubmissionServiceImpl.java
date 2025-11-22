package com.emat.reapi.submission;

import com.emat.reapi.api.dto.SubmissionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    @Override
    public Mono<Submission> findBySubmissionId(String submissionId) {
        return null;
    }

    @Override
    public Mono<Submission> createSubmission(SubmissionDto request) {
        log.info("Creating submission for clientId: {}", request.clientId());
        var document = new SubmissionDocument();
        document.setSubmissionId("sub_" + UUID.randomUUID());
        document.setClientId(request.clientId());
        document.setClientName(request.clientName());
        document.setTestName(request.testName());
        document.setStatus(SubmissionStatus.OPEN);
        document.setDuration(request.duration());
        document.setExpireAt(Instant.now().plusSeconds(request.duration()));

        return submissionRepository.save(document)
                .map(SubmissionDocument::toDomain)
                .doOnSuccess(sa -> log.info("Submission ID: '{}' created successful", sa.submissionId()))
                .onErrorMap(e -> {
                    log.error("Error creating submission for clientId: {}", request.clientId(), e);
                    return new SubmissionCreateException("Failed to create submission", e);
                });
    }

    @Override
    public Flux<Submission> findAllByOrderByCreatedAtDesc() {
        return submissionRepository.findAllByOrderByCreatedAtDesc()
                .map(SubmissionDocument::toDomain);
    }

    @Override
    public Mono<Void> closeSubmission() {
        return null;
    }
}
