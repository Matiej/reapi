package com.emat.reapi.api.dto.fptestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FpTestDto(
        String testId,

        @NotBlank(message = "Field testId can't be empty!")
        @Size(max = 255, message = "To long test name Maximum size of testId filed is 255 characters.")
        String testName,

        @Size(max = 10_000, message = "To long description, value of descriptionBefore field can't be longer than 10_000 characters")
        String descriptionBefore,

        @Size(max = 10_000, message = "To long description, value of descriptionAfter field can't be longer than 10_000 characters")
        String descriptionAfter,

        List<String> statementKeys
) {
        public FpTestDto addTestId(String testId) {
                return new FpTestDto(
                        testId,
                        this.testName,
                        this.descriptionBefore,
                        this.descriptionAfter,
                        this.statementKeys
                );
        }
}
