package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.reportjob.ReportJob;
import com.emat.reapi.profiler.infra.ReportJobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
class ReportJobService {
    private final ReportJobRepository repository;

    public Mono<ReportJob> getReportJobBySubmission(String submissionId) {
        return repository.findBySubmissionId(submissionId)
                .map(ReportJob::fromDocument);
    }

    public Mono<ReportJob> save(ReportJob job) {
        return repository.save(job.toDocument()).map(ReportJob::fromDocument);
    }

}
