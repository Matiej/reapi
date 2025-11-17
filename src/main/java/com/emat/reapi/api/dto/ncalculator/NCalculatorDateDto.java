package com.emat.reapi.api.dto.ncalculator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record NCalculatorDateDto(
        @NotBlank(message = "birthDate cannot be empty.")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "birthDate must match format yyyy-MM-dd."
        )
        String birthDate,
        @NotBlank(message = "referenceDate cannot be empty.")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "referenceDate must match format yyyy-MM-dd."
        )
        String referenceDate
) {
}
