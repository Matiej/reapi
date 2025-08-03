package com.emat.reapi.ai;

import java.util.Locale;

public record EmbeddingMetaRequest(
        String clientId,
        String documentType,
        Locale language
) {

}
