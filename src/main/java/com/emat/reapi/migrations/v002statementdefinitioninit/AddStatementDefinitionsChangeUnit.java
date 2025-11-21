package com.emat.reapi.migrations.v002statementdefinitioninit;

import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.infra.StatementDefinitionDocument;
import com.emat.reapi.statement.infra.dictionary.StatementDefinitionsDictionary;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Slf4j
@ChangeUnit(
        id = "v002_statement-definitions.add",
        order = "002",
        author = "profiler-service"
)
@AllArgsConstructor
public class AddStatementDefinitionsChangeUnit {
    private final MongoTemplate mongoTemplate;

    @Execution
    public void execution() {
        long count = mongoTemplate.getCollection(StatementDefinitionDocument.COLLECTION_NAME).countDocuments();
        if (count > 0) {
            log.info("Collection {} already contains {} documents. Skipping seed.",
                    StatementDefinitionDocument.COLLECTION_NAME, count);
            return;
        }

        List<StatementDefinition> allDefinitions = StatementDefinitionsSeed.ALL;

        List<StatementDefinitionDocument> documents = allDefinitions.stream()
                .map(StatementDefinitionDocument::toDocument)
                .toList();

        mongoTemplate.insert(documents, StatementDefinitionDocument.COLLECTION_NAME);

        log.info("Inserted {} statement definitions into collection {}",
                documents.size(), StatementDefinitionDocument.COLLECTION_NAME);
    }

    @RollbackExecution
    public void rollback() {
        List<String> statementIds = StatementDefinitionsDictionary.ALL.stream()
                .map(StatementDefinition::getStatementId)
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        Query query = Query.query(
                Criteria.where("statementId").in(statementIds)
        );

        var result = mongoTemplate.remove(
                query,
                StatementDefinitionDocument.COLLECTION_NAME
        );

        log.info("Rollback: removed {} statement definitions from collection {}",
                result.getDeletedCount(), StatementDefinitionDocument.COLLECTION_NAME);
    }
}
