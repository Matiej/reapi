package com.emat.reapi.ai.validator;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class TextValidator {
    public void validate(String text, int maxCharacters) {
        if (text == null || text.trim().isEmpty()) {
            throw new ValidationException("Text is empty.");
        }

        if (text.length() > maxCharacters) {
            throw new ValidationException("Text exceeds " + maxCharacters + " characters.");
        }
    }
}
