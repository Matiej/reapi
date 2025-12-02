package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class ScoringProfiledClientDetails {
    private String clientName;
    private String clientId;
    private String submissionId;
    private Instant submissionDate;
    private String testId;
    private String testName;
    private Instant clientTestDate;
}
