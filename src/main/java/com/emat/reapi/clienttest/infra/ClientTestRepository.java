package com.emat.reapi.clienttest.infra;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTestRepository extends ReactiveMongoRepository<ClientTestDocument, String> {
}
