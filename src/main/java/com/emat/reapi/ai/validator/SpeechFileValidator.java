package com.emat.reapi.ai.validator;

import org.springframework.stereotype.Component;

@Component
public class SpeechFileValidator extends AbstractTextFileValidator{
    @Override
    protected int maxCharLength() {
        return 4000;
    }
}
