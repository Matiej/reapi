package com.emat.reapi.api.dto;

//todo create validations for each filed
public record SubmissionDto(
        String clientId,
        String clientName,
        String testName,
        int durationMin
) {
}
