package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ScoringCategoryBlock {
    private ProfileCategory category;
    private int totalAnswers;
    private int totalScore;
    private double avgScore;
    private Map<String, Long> scoreBuckets;
    private List<ScoringStatementPair> answersBySeverity;
}
