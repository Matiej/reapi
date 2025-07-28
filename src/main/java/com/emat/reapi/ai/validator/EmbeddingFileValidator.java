package com.emat.reapi.ai.validator;

import org.springframework.stereotype.Component;

@Component
public class EmbeddingFileValidator extends AbstractTextFileValidator{
    @Override
    protected int maxCharLength() {
        return 10_000;
    }
}
