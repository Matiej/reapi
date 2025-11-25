package com.emat.reapi.migrations.v005statementKeyidxadd;

import com.emat.reapi.statement.infra.StatementDefinitionDocument;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Slf4j
@AllArgsConstructor
@ChangeUnit(
        id = "v005_statementDefinitions-key-index.add",
        order = "005",
        author = "profiler-service"
)
public class StatementDefinitionKeyIndexAdd {
    private static final String INDEX_NAME = "statementKey_idx";
    private final MongoTemplate mongoTemplate;

    @Execution
    public void execution() {
        var index = new Index()
                .on("statementKey", Sort.Direction.ASC)
                .named(INDEX_NAME)
                .unique()
                .background();
        mongoTemplate.indexOps(StatementDefinitionDocument.COLLECTION_NAME)
                .ensureIndex(index);
        log.info("Ensured index {} on collection {}", INDEX_NAME, StatementDefinitionDocument.COLLECTION_NAME);
    }

    @RollbackExecution
    public void rollback() {
        // no-ops
    }
}
