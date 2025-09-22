package com.emat.reapi.profiler.port;

import com.emat.reapi.profiler.domain.ProfiledClientAnswer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProfiledService {

    Mono<ProfiledClientAnswer> getClientProfiledStatements(String clientId);

    Mono<ProfiledClientAnswer> getClientProfiledStatement(String submissionId);

}
