package com.emat.reapi.api;

import com.emat.reapi.api.dto.ncalculator.NCalculatorPhraseDto;
import com.emat.reapi.ncalculator.domain.NumerologyPhraseCalculator;
import com.emat.reapi.ncalculator.port.NCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ncalculator")
@Slf4j
@AllArgsConstructor
@Validated
public class NCalculatorController {
    private final NCalculatorService nCalculatorService;

    @Operation(
            summary = "Calculate numerology vibration for phrase",
            description = "Calculates numerological sums for vowels and consonants in a given phrase (including Polish letters). Digits are treated 1:1 and added to total vibration.",
            responses = @ApiResponse(responseCode = "200", description = "Calculated successfully")
    )
    @PostMapping("/phrase")
    @ResponseStatus(HttpStatus.OK)
    public Mono<NumerologyPhraseCalculator> calculatePhrase(
            @Valid @RequestBody NCalculatorPhraseDto request
    ) {
        log.info("Received request: POST '/api/ncalculator/phrase' with phrase='{}'", request.phrase());
        return nCalculatorService.calculatePhrase(request.phrase());
    }
}
