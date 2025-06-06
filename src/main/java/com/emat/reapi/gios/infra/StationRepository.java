package com.emat.reapi.gios.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StationRepository extends ReactiveMongoRepository<StationDocument, String> {

    Mono<StationDocument> findByStationId(String stationId);
}
