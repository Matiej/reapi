package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ScoringOverallSummary {
    private int totalAnswers;
    private int totalScore;
    private Map<String, Long> scoreBuckets;
}
