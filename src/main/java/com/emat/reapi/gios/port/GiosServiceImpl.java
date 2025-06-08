package com.emat.reapi.gios.port;

import com.emat.reapi.gios.domain.GiosAqIndex;
import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.domain.Station;
import com.emat.reapi.gios.infra.StationDocument;
import com.emat.reapi.gios.infra.StationRepository;
import com.emat.reapi.gios.integration.gios.GiosAqIndexResponse;
import com.emat.reapi.gios.integration.gios.GiosClient;
import com.emat.reapi.gios.integration.gios.GiosStationsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
class GiosServiceImpl implements GiosService {
    private final GiosClient giosClient;
    private final StationRepository repository;

    @Override
    public Mono<GiosStations> synchronizeStations() {
        return giosClient.getAllStations()
                .doOnNext(r -> log.info("Received {} stations from GIOS", r.getNumberOfStations()))
                .map(GiosStationsResponse::toDomain)
                .flatMap(stations ->
                        //might be risky to use mono.when, saving and updataing the same threads. Check why
                        Mono.when(
                                        updateExistingStations(stations.getStationList()),
                                        saveNewStations(stations.getStationList())
                                )
                                .thenReturn(stations));
    }

    @Override
    public Mono<GiosAqIndex> getAqIndex(String stationId) {
        return   giosClient.getAqIndex(stationId)
                .map(GiosAqIndexResponse::toDomain)
                .doOnNext(r -> log.info("Received aqIndex for station {}", stationId));
    }


    private Mono<Void> updateExistingStations(List<Station> stationList) {
        return Flux.fromIterable(stationList)
                .flatMap(station -> {
                    StationDocument newStationDocument = station.toDocument();
                    return repository.findByStationId(newStationDocument.getStationId())
                            .flatMap(existingStationDocument -> {
                                if (existingStationDocument.equals(newStationDocument)) {
                                    return Mono.empty();
                                }
                                newStationDocument.setId(existingStationDocument.getId());
                                return repository.save(newStationDocument);
                            })
                            .doOnNext(saved -> {
                                log.info("Updating station id:{}", saved.getStationId());
                            });
                })
                .then();
    }

    private Mono<Void> saveNewStations(List<Station> stationList) {
        AtomicInteger newStationsCounter = new AtomicInteger(0);
        return Flux.fromIterable(stationList)
                .flatMap(station -> {
                    StationDocument newStationDocument = station.toDocument();
                    return repository.findByStationId(newStationDocument.getStationId())
                            .switchIfEmpty(repository.save(newStationDocument)
                                    .doOnSuccess(saved -> {
                                        log.info("Saved station id: {}", saved.getStationId());

                                    }));
                })
                .then();
    }
}
