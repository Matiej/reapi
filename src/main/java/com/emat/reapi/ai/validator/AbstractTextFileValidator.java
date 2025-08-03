package com.emat.reapi.ai.validator;

import jakarta.validation.ValidationException;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractTextFileValidator {
    protected abstract int maxCharLength();

    public void Validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ValidationException("Uploaded file is empty.");
        }

        if (!"text/plain".equalsIgnoreCase(file.getContentType())) {
            throw new ValidationException("Only .txt files with text/plain MIME type are allowed.");
        }

        long maxBytes = estimateMaxFileSizeBytes();
        if (file.getSize() > maxBytes) {
            throw new ValidationException("File is to big limit: " + maxBytes / 1024 + " KB.");
        }
    }

    private long estimateMaxFileSizeBytes() {
        return (long) (maxCharLength() * 1.5);
    }

    public int getMaxCharLength() {
        return maxCharLength();
    }

}
