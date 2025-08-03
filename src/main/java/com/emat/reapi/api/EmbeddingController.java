package com.emat.reapi.api;

import com.emat.reapi.ai.EmbeddingMetaRequest;
import com.emat.reapi.ai.port.ReApiEmbeddingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Locale;

@RestController
@Slf4j
@Tag(name = "Embeddings", description = "Text embedding and indexing with OpenAI")
@Validated
@AllArgsConstructor
@RequestMapping("reapi/embeddings")
public class EmbeddingController {
    private final ReApiEmbeddingService reApiEmbeddingService;

    @Operation(
            summary = "Index text file to vector store",
            description = "Uploads a UTF-8 encoded `.txt` file and indexes its content using OpenAI embeddings.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Text indexed successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or file format"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public Mono<Void> indexTextFromFile(
            @RequestPart("file")
            @Parameter(
                    description = "Text file (.txt) in UTF-8 format with max allowed size defined by validator",
                    required = true,
                    content = @Content(mediaType = "text/plain")
            )
            MultipartFile file,

            @RequestParam("clientId")
            @NotBlank
            @Parameter(description = "Unique client identifier", required = true)
            String clientId,

            @RequestParam("documentType")
            @NotBlank
            @Parameter(description = "Document type for indexing context", required = true)
            String documentType,

            @RequestParam("language")
            @Parameter(description = "Language of the document (e.g., 'en', 'pl')", required = true)
            Locale language
    ) {
        log.info("received request: GET /reapi/embeddings/file");
        return reApiEmbeddingService.indexText(file, new EmbeddingMetaRequest(clientId, documentType, language));
    }

    @PostMapping(value = "/text" )
    public Mono<Void> indexTextFromRawText(
            @RequestParam("text")
            @NotBlank
            @Parameter(
                    description = "Raw text input to be embedded",
                    required = true,
                    schema = @Schema(type = "string")
            )
            String text,

            @RequestParam("clientId")
            @NotBlank
            @Parameter(description = "Unique client identifier", required = true)
            String clientId,

            @RequestParam("documentType")
            @NotBlank
            @Parameter(description = "Document type for indexing context", required = true)
            String documentType,

            @RequestParam("language")
            @Parameter(description = "Language of the document (e.g., 'en', 'pl')", required = true)
            Locale language
    ) {
        log.info("received POST /reapi/embeddings/text");
        return reApiEmbeddingService.indexText(text, new EmbeddingMetaRequest(clientId, documentType, language));
    }
}

