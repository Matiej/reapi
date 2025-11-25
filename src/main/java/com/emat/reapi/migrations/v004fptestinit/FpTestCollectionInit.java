package com.emat.reapi.migrations.v004fptestinit;

import com.emat.reapi.fptest.infra.FpTestDocument;
import com.emat.reapi.migrations.CollectionInitializer;
import io.mongock.api.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Slf4j
@AllArgsConstructor
@ChangeUnit(
        id = "v004_pftest-collection.add",
        order = "004",
        author = "profiler-service"
)
public class FpTestCollectionInit {
    private final MongoTemplate mongoTemplate;
    private final MongoMappingContext mongoMappingContext;

    @BeforeExecution
    public void beforeExecution() {
        CollectionInitializer.initializeCollection(
                mongoTemplate,
                mongoMappingContext,
                FpTestDocument.COLLECTION_NAME,
                FpTestDocument.class
        );
        log.info("Initialized collection: {}", FpTestDocument.COLLECTION_NAME);
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
