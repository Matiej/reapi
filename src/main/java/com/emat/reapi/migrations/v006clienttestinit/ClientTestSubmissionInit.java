package com.emat.reapi.migrations.v006clienttestinit;

import com.emat.reapi.clienttest.infra.ClientTestDocument;
import com.emat.reapi.migrations.CollectionInitializer;
import io.mongock.api.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Slf4j
@AllArgsConstructor
@ChangeUnit(
        id = "v006_client-test-submissions-document.init",
        order = "006",
        author = "profiler-service"
)
public class ClientTestSubmissionInit {
    private final MongoTemplate mongoTemplate;
    private final MongoMappingContext mongoMappingContext;

    @BeforeExecution
    public void beforeExecution() {
        CollectionInitializer.initializeCollection(
                mongoTemplate,
                mongoMappingContext,
                ClientTestDocument.COLLECTION_NAME,
                ClientTestDocument.class
        );
        log.info("Initialized collection: {}", ClientTestDocument.COLLECTION_NAME);
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        //no-ops
    }

    @Execution
    public void execution() {
        //  no-ops
    }

    @RollbackExecution
    public void rollback() {
        // no-ops
    }

}
