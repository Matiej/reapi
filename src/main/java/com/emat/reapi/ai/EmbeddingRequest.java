package com.emat.reapi.ai;

import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

public record EmbeddingRequest(
    MultipartFile file,
    String clientId,
    String documentType,
    Locale language
) {

}
