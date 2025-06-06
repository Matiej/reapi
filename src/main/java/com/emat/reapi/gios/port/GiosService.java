package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosStations;
import reactor.core.publisher.Mono;

public interface GiosService {
    Mono<GiosStations> synchronizeStations();
}
