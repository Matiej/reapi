package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.integration.gios.GiosClient;
import com.emat.reapi.gios.integration.gios.GiosStationsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
class GiosServiceImpl implements GiosService {
    private final GiosClient giosClient;

    @Override
    public Mono<GiosStations> findAllStations() {
        return giosClient.getAllStations()
                .doOnNext(r -> log.info("Received {} stations from GIOS", r.getNumberOfStations()))
                .map(GiosStationsResponse::toDomain);
    }
}
