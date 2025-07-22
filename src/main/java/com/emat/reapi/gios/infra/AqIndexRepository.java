package com.emat.reapi.gios.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AqIndexRepository extends ReactiveMongoRepository<AqIndexDocument, String> {
}
