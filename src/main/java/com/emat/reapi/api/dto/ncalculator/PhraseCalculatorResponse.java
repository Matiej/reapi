package com.emat.reapi.api.dto.ncalculator;

import com.emat.reapi.ncalculator.domain.NumerologyPhraseCalculator;

public record PhraseCalculatorResponse(
        int vowelsResult,
        int consonantsResult,
        String vibration
) {

    PhraseCalculatorResponse fromDomain(NumerologyPhraseCalculator numerologyPhraseCalculator) {
        return new PhraseCalculatorResponse(
                numerologyPhraseCalculator.getVowelsResult(),
                numerologyPhraseCalculator.getConsonantsResult(),
                numerologyPhraseCalculator.getVibration()
        );
    }
}
