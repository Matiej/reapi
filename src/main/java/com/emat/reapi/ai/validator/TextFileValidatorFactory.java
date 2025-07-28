package com.emat.reapi.ai.validator;

import org.springframework.stereotype.Component;

@Component
public class TextFileValidatorFactory {
    private final SpeechFileValidator speechFileValidator;
    private final EmbeddingFileValidator embeddingFileValidator;

    public TextFileValidatorFactory(SpeechFileValidator speechFileValidator, EmbeddingFileValidator embeddingFileValidator) {
        this.speechFileValidator = speechFileValidator;
        this.embeddingFileValidator = embeddingFileValidator;
    }

    public AbstractTextFileValidator getValidator(FileValidationType type) {
        return switch (type) {
            case SPEECH -> speechFileValidator;
            case EMBEDDING -> embeddingFileValidator;
        };
    }
}
