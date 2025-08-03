package com.emat.reapi.ai.port;

import com.emat.reapi.ai.EmbeddingMetaRequest;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ReApiEmbeddingService {
    Mono<Void> indexText(MultipartFile file, EmbeddingMetaRequest request);

    Mono<Void> indexText(String text, EmbeddingMetaRequest request);
}
