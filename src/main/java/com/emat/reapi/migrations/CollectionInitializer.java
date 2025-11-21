package com.emat.reapi.migrations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Slf4j
public class CollectionInitializer {
    public static void initializeCollection(
            MongoTemplate mongoTemplate,
            MongoMappingContext mappingContext,
            String collectionName,
            Class<?> docClass
    ) {
        createIfMissing(mongoTemplate, collectionName);
        var resolver = new MongoPersistentEntityIndexResolver(mappingContext);
        resolver.resolveIndexFor(docClass).forEach(indexDefinition -> {
            mongoTemplate.indexOps(collectionName).ensureIndex(indexDefinition);
            log.info("Ensured index {} on collection {}", indexDefinition.getIndexKeys(), collectionName);
        });

    }

    private static void createIfMissing(MongoTemplate mongoTemplate, String collectionName) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
            log.info("Collection; {} created successful", collectionName);
        } else {
            log.info("Collection: {} already exists", collectionName);
        }
    }
}
