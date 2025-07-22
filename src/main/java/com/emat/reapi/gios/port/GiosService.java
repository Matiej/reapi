package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosAqIndex;
import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.infra.AqIndexDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GiosService {
    Mono<GiosStations> synchronizeStations();
    Mono<GiosAqIndex> getAqIndex(String stationId);
    Flux<AqIndexDocument> saveMeasurementsForAllStations();
}
