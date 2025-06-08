package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosAqIndex;
import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.integration.gios.GiosAqIndexResponse;
import reactor.core.publisher.Mono;

public interface GiosService {
    Mono<GiosStations> synchronizeStations();
    Mono<GiosAqIndex> getAqIndex(String stationId);
}
