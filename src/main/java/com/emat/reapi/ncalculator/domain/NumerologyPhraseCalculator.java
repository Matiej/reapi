package com.emat.reapi.ncalculator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumerologyPhraseCalculator {
    private int vowelsResult;
    private int consonantsResult;
    private String vibration;
}
