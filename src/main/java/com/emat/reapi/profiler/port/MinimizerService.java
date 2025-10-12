package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.MinimizedPayload;
import com.emat.reapi.profiler.domain.ProfiledClientAnswerDetails;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import reactor.core.publisher.Mono;

public interface MinimizerService {
    Mono<MinimizedPayload> minimize(ProfiledClientAnswerDetails profiled, PayloadMode mode);
}
