package com.emat.reapi.ncalculator.port;

import com.emat.reapi.ncalculator.domain.LetterVibrationsDictionary;
import com.emat.reapi.ncalculator.domain.NumerologyDatesCalculator;
import com.emat.reapi.ncalculator.domain.NumerologyPhraseCalculator;
import com.emat.reapi.ncalculator.infra.NumerologyPhaseCalculatorDocument;
import com.emat.reapi.ncalculator.infra.NumerologyPhaseCalculatorRepository;
import io.swagger.v3.oas.annotations.callbacks.Callbacks;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.emat.reapi.ncalculator.domain.LetterVibrationsDictionary.VOWELS;

@Slf4j
@Service
@AllArgsConstructor
public class NCalculatorServiceImpl implements NCalculatorService {
    private final NumerologyPhaseCalculatorRepository numerologyPhaseCalculatorRepository;

    @Override
    public Mono<NumerologyPhraseCalculator> calculatePhrase(String phrase) {
        if (phrase == null) {
            return Mono.error(new IllegalArgumentException("Phrase cannot be null"));
        }
        var normalizedPhrase = phrase.replace(" ", "").toUpperCase();
        int vowelsSum = 0;
        int consonantsSum = 0;
        int totalSum = 0;

        for (int i = 0; i < normalizedPhrase.length(); i++) {
            char ch = normalizedPhrase.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }

            if (Character.isDigit(ch)) {
                int digit = Character.digit(ch, 10);
                totalSum += digit;
                consonantsSum += digit;
                continue;
            }

            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                int value = LetterVibrationsDictionary.LETTER_VIBRATION.getOrDefault(upper, 0);
                totalSum += value;

                if (LetterVibrationsDictionary.VOWELS.contains(upper)) {
                    vowelsSum += value;
                } else {
                    consonantsSum += value;
                }
            }
        }
        int reduced = reduceToSingleDigit(totalSum);
        String vibration = totalSum + "/" + reduced;
        NumerologyPhraseCalculator result = new NumerologyPhraseCalculator(
                vowelsSum,
                consonantsSum,
                vibration
        );
        NumerologyPhaseCalculatorDocument document = new NumerologyPhaseCalculatorDocument(
                null,
                normalizedPhrase,
                vowelsSum,
                consonantsSum,
                vibration,
                null,
                null,
                null
        );
        return numerologyPhaseCalculatorRepository
                .save(document)
                .doOnSuccess(saved ->
                        log.info("Saved numerology phrase calculation for phrase='{}', id={}",
                                normalizedPhrase, saved.getId()))
                .map(saved -> result);
    }

    private int reduceToSingleDigit(int number) {
        int n = Math.abs(number);
        while (n > 9) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        return n;
    }

    @Override
    public Mono<NumerologyDatesCalculator> calculateDates(String birthDate, String referenceDate) {
        return null;
    }
}
