package com.emat.reapi.ai.port;

import com.emat.reapi.ai.EmbeddingRequest;
import com.emat.reapi.ai.integration.OpenAiClientFactory;
import com.emat.reapi.ai.validator.TextFileValidatorFactory;
import com.emat.reapi.ai.validator.TextValidator;
import jakarta.validation.ValidationException;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.emat.reapi.ai.validator.FileValidationType.EMBEDDING;

@Service
public class ReApiEmbeddingServiceImpl {
    private final OpenAiClientFactory openAiClientFactory;
    private final TextFileValidatorFactory fileValidatorFactory;
    private final TextValidator textVal;
    private final TextChunksSplitter textChunkSplitter;

    private static final int CHUNK_SIZE = 2000;

    public ReApiEmbeddingServiceImpl(OpenAiClientFactory openAiClientFactory, TextFileValidatorFactory fileValidatorFactory, TextValidator textVal, TextChunksSplitter textChunkSplitter) {
        this.openAiClientFactory = openAiClientFactory;
        this.fileValidatorFactory = fileValidatorFactory;
        this.textVal = textVal;
        this.textChunkSplitter = textChunkSplitter;
    }

    public void indexText(EmbeddingRequest request) {
        var validatedText = getValidatedText(request);
        OpenAiEmbeddingModel embeddingModel = openAiClientFactory.createEmbeddingModel();
        var vectorStore = SimpleVectorStore.builder(embeddingModel).build();

        List<String> chunks = textChunkSplitter.splitBySentences(validatedText, CHUNK_SIZE);
        String sourceId = UUID.randomUUID().toString();

        IntStream.range(0, chunks.size()).forEach(i -> {
            String chunk = chunks.get(i).trim();
            if (chunk.isEmpty()) return;

            Document doc = new Document(chunk);
            doc.getMetadata().put("clientId", request.clientId());
            doc.getMetadata().put("documentType", request.documentType());
            doc.getMetadata().put("language", request.language().getLanguage());
            doc.getMetadata().put("chunkIndex", String.valueOf(i));
            doc.getMetadata().put("uploadedAt", Instant.now().toString());
            doc.getMetadata().put("sourceId", sourceId);
            doc.getMetadata().put("fileName", request.file().getOriginalFilename());

            vectorStore.add(List.of(doc));
        });
    }

    private String getValidatedText(EmbeddingRequest request) {
        var validator = fileValidatorFactory.getValidator(EMBEDDING);
        int maxCharLength = validator.getMaxCharLength();
        MultipartFile file = request.file();
        validator.Validate(file);
        String text = extract(file);
        textVal.validate(text, maxCharLength);
        return text;
    }

    private String extract(MultipartFile file) {
        try {
            return new String(file.getBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            throw new ValidationException("Can't read text file!.");
        }
    }
}
