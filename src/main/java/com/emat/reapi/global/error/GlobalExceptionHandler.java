package com.emat.reapi.global.error;

import com.emat.reapi.submission.SubmissionException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SubmissionException.class)
    public ErrorResponse handleSubmissionException(SubmissionException ex, ServerHttpRequest request) {
        log.warn("SubmissionException: {}", ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                ex.getType().name(),
                ex.getMessage(),
                request.getPath().value(),
                null
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatus(ResponseStatusException ex, ServerHttpRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        log.warn("ResponseStatusException {} at {}: {}", status, request.getPath(), ex.getReason());
        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                "GENERIC_STATUS_ERROR",
                ex.getReason(),
                request.getPath().value(),
                null
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorResponse handleBindException(WebExchangeBindException ex, ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> details = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fe -> fe.getField(),
                        fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "Invalid value",
                        (a, b) -> b
                ));

        log.warn("Validation error at {}: {}", request.getPath(), details);

        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                "VALIDATION_ERROR",
                "Validation failed",
                request.getPath().value(),
                details
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException ex,
                                                   ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> details = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        v -> v.getMessage(),
                        (a, b) -> b
                ));

        log.warn("Constraint violation at {}: {}", request.getPath(), details);

        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                "CONSTRAINT_VIOLATION",
                "Constraint violation",
                request.getPath().value(),
                details
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgument(IllegalArgumentException ex, ServerHttpRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.warn("IllegalArgument at {}: {}", request.getPath(), ex.getMessage());
        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                "ILLEGAL_ARGUMENT",
                ex.getMessage(),
                request.getPath().value(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneric(Exception ex, ServerHttpRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Unexpected error at {}: {}", request.getPath(), ex.getMessage(), ex);
        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                "INTERNAL_ERROR",
                "Unexpected server error",
                request.getPath().value(),
                null
        );
    }

}
