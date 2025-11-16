package com.emat.reapi.api.dto.ncalculator;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NCalculatorPhraseDto(
        @Pattern(
                regexp = "^[\\p{L}\\p{N} ]{1,100}$",
                message = "This filed can contain only letters (including polish) digits and spaces.."
        )
        @Size(max = 100, message = "Maximum length is 100 characters.")
        String phrase
) {
}
