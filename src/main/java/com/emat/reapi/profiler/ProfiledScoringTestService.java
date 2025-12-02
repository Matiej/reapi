package com.emat.reapi.profiler;

import com.emat.reapi.profiler.domain.ScoringProfiledShort;
import com.emat.reapi.profiler.domain.ScoringProfiledClientDetails;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfiledScoringTestService {
    Mono<ScoringProfiledClientDetails> getScoringProfile(String submissionId);
    Mono<List<ScoringProfiledShort>> getScoringShortProfiles();
}
