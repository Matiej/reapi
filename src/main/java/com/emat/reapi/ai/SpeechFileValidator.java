package com.emat.reapi.ai;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SpeechFileValidator {
    private static final int MAX_CHARACTERS = 4000;


    public String validateAndExtractText(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ValidationException("Uploaded file is empty.");
        }

        if (!"text/plain".equalsIgnoreCase(file.getContentType())) {
            throw new ValidationException("Only .txt files with text/plain MIME type are allowed.");
        }

        String text;
        try {
            text = new String(file.getBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            throw new ValidationException("Failed to read file content.");
        }

        if (text.length() > MAX_CHARACTERS) {
            throw new ValidationException("Text exceeds 4000 characters. Please upload a shorter file.");
        }

        if (text.isEmpty()) {
            throw new ValidationException("Text file contains no readable characters.");
        }

        return text;
    }
}
