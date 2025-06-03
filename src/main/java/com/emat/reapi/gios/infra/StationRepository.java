package com.emat.reapi.gios.infra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends ReactiveMongoRepository<StationDocument, String> {
}
