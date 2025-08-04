package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnsweredStatement {
    private String statementId;
    private int score;// from 02 (left side) to +2 (right side)
}
