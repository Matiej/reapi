package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.domain.Station;
import com.emat.reapi.gios.infra.StationDocument;
import com.emat.reapi.gios.infra.StationRepository;
import com.emat.reapi.gios.integration.gios.GiosClient;
import com.emat.reapi.gios.integration.gios.GiosStationsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
class GiosServiceImpl implements GiosService {
    private final GiosClient giosClient;
    private final StationRepository repository;

    @Override
    public Mono<GiosStations> findAllStations() {
        return giosClient.getAllStations()
                .doOnNext(r -> log.info("Received {} stations from GIOS", r.getNumberOfStations()))
                .map(GiosStationsResponse::toDomain)
                .flatMap(stations -> saveAllStations(stations.getStationList())
                        .thenReturn(stations));
    }

    private Mono<Void> saveAllStations(List<Station> stationList) {
        return Flux.fromIterable(stationList)
                .map(Station::toDocument)
                .collectList()
                .flatMapMany(repository::saveAll)
                .then();
    }
}
