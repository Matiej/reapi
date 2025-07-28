package com.emat.reapi.ai.port;

import com.emat.reapi.ai.integration.OpenAiClientFactory;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ReApiEmbeddingServiceImpl {
    private final OpenAiClientFactory openAiClientFactory;

    public ReApiEmbeddingServiceImpl(OpenAiClientFactory openAiClientFactory) {
        this.openAiClientFactory = openAiClientFactory;
    }

    public void idexText(String text, String sourceId) {
        OpenAiEmbeddingModel embeddingModel = openAiClientFactory.createEmbeddingModel();
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();

    }


}
