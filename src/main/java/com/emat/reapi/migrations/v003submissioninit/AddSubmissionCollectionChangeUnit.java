package com.emat.reapi.migrations.v003submissioninit;

import com.emat.reapi.migrations.CollectionInitializer;
import com.emat.reapi.submission.infra.SubmissionDocument;
import io.mongock.api.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Slf4j
@ChangeUnit(
        id = "v003_submission-collection.add",
        order = "003",
        author = "profiler-service"
)
@AllArgsConstructor
public class AddSubmissionCollectionChangeUnit {
    private final MongoTemplate mongoTemplate;
    private final MongoMappingContext mongoMappingContext;

    @BeforeExecution
    public void beforeExecution() {
        CollectionInitializer.initializeCollection(
                mongoTemplate,
                mongoMappingContext,
                SubmissionDocument.COLLECTION_NAME,
                SubmissionDocument.class
        );
        log.info("Initialized collection: {}", SubmissionDocument.COLLECTION_NAME);
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
