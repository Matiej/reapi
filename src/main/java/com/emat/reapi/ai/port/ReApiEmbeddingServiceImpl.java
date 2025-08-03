package com.emat.reapi.ai.port;

import com.emat.reapi.ai.EmbeddingMetaRequest;
import com.emat.reapi.ai.integration.OpenAiClientFactory;
import com.emat.reapi.ai.validator.TextFileValidatorFactory;
import com.emat.reapi.ai.validator.TextValidator;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.emat.reapi.ai.validator.FileValidationType.EMBEDDING;

@Service
@AllArgsConstructor
@Slf4j
class ReApiEmbeddingServiceImpl implements ReApiEmbeddingService {
    private final OpenAiClientFactory openAiClientFactory;
    private final TextValidator textVal;
    private final TextChunksSplitter textChunkSplitter;
    private final TextFileValidatorFactory fileValidatorFactory;
    private final SimpleVectorStore vectorStore;

    private static final int CHUNK_SIZE = 2000;

    @Override
    public Mono<Void> indexText(MultipartFile file, EmbeddingMetaRequest request) {
        return Mono.fromRunnable(() -> {
            log.info("Starting indexing from file for clientId={}, documentType={}, language={}",
                    request.clientId(), request.documentType(), request.language());
            var validatedText = getValidatedText(file);

            storeTextAsEmbeddings(validatedText, request, file.getOriginalFilename());
            log.info("Indexing file text completed successfully.");
        });
    }

    @Override
    public Mono<Void> indexText(String text, EmbeddingMetaRequest request) {
        return Mono.fromRunnable(() -> {
            log.info("Starting indexing from text for clientId={}, documentType={}, language={}",
                    request.clientId(), request.documentType(), request.language());

            var maxCharLength = fileValidatorFactory.getValidator(EMBEDDING).getMaxCharLength();
            log.debug("Validating raw text (maxLength={})...", maxCharLength);

            textVal.validate(text, maxCharLength);

            storeTextAsEmbeddings(text, request, null);

            log.info("Indexing from raw text completed successfully.");
        });
    }

    private void storeTextAsEmbeddings(String validatedText, EmbeddingMetaRequest request, String fileName) {
        OpenAiEmbeddingModel embeddingModel = openAiClientFactory.createEmbeddingModel();

        List<String> chunks = textChunkSplitter.splitBySentences(validatedText, CHUNK_SIZE);
        log.info("Text split into {} chunks (maxChunkSize={})", chunks.size(), CHUNK_SIZE);

        String sourceId = UUID.randomUUID().toString();
        log.info("Starting embedding creation (sourceId={}, length={} chars)", sourceId, validatedText.length());

        List<Document> docs = createDocuments(chunks, request, fileName, sourceId);
        vectorStore.add(docs);

        log.info("All chunks embedded successfully for sourceId={}", sourceId);
    }

    private List<Document> createDocuments(List<String> chunks, EmbeddingMetaRequest request, String fileName, String sourceId) {
        return IntStream.range(0, chunks.size())
                .mapToObj(i -> {
                    String chunk = chunks.get(i).trim();
                    if (chunk.isEmpty()) {
                        log.warn("Skipping empty chunk at index {}", i);
                        return null;
                    }
                    Document doc = toDocument(chunk, i, request, sourceId, fileName);
                    log.debug("➕ Embedded chunk {}/{} added ({} chars)", i + 1, chunks.size(), chunk.length());
                    return doc;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private Document toDocument(String chunk, int index, EmbeddingMetaRequest request, String sourceId, String fileName) {
        Document doc = new Document(chunk);
        doc.getMetadata().put("clientId", request.clientId());
        doc.getMetadata().put("documentType", request.documentType());
        doc.getMetadata().put("language", request.language().getLanguage());
        doc.getMetadata().put("chunkIndex", String.valueOf(index));
        doc.getMetadata().put("uploadedAt", Instant.now().toString());
        doc.getMetadata().put("sourceId", sourceId);
        if (fileName != null) {
            doc.getMetadata().put("fileName", fileName);
        }
        return doc;
    }

    private String getValidatedText(MultipartFile file) {
        var validator = fileValidatorFactory.getValidator(EMBEDDING);
        int maxCharLength = validator.getMaxCharLength();
        log.debug("🔎 Validating text form file (maxLength={})...", maxCharLength);
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
