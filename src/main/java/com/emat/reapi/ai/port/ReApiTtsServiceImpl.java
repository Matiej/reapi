package com.emat.reapi.ai.port;

import com.emat.reapi.ai.TtsRequest;
import com.emat.reapi.ai.integration.OpenAiClientFactory;
import com.emat.reapi.ai.validator.TextFileValidatorFactory;
import com.emat.reapi.ai.validator.TextValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.emat.reapi.ai.validator.FileValidationType.SPEECH;

@Service
@Slf4j
@RequiredArgsConstructor
class ReApiTtsServiceImpl implements ReApiTtsService {

    private final OpenAiClientFactory openAiClientFactory;
    private final TextFileValidatorFactory fileValidatorFactory;
    private final TextValidator textValidator;

    @Override
    public Mono<byte[]> generateSpeech(TtsRequest ttsRequest) {
        String text = getValidatedText(ttsRequest);

        return ttsRequest.isHd() ? generateProSpeech(text, ttsRequest.getVoice(), ttsRequest.getAudioFormat())
                : generateBasicSpeech(text, ttsRequest.getVoice(), ttsRequest.getAudioFormat());
    }

    private String getValidatedText(TtsRequest request) {
        var validator = fileValidatorFactory.getValidator(SPEECH);
        int maxCharLength = validator.getMaxCharLength();
        if (request.getFile() == null) {
            String text = request.getText();
            textValidator.validate(text, maxCharLength);
            return text;
        } else {
            MultipartFile file = request.getFile();
            validator.Validate(file);
            String text = extract(file);
            textValidator.validate(text, maxCharLength);
            return text;
        }
    }

    private Mono<byte[]> generateBasicSpeech(String text,
                                             OpenAiAudioApi.SpeechRequest.Voice voice,
                                             OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        log.info("Generating TTS basic audio for text: {}", text);
        return generate(text, voice, audioFormat, OpenAiAudioApi.TtsModel.TTS_1);
    }

    private Mono<byte[]> generateProSpeech(String text, OpenAiAudioApi.SpeechRequest.Voice voice, OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        log.info("Generating TTS pro HD audio for text: {}", text);
        return generate(text, voice, audioFormat, OpenAiAudioApi.TtsModel.TTS_1_HD);
    }

    private Mono<byte[]> generate(String text,
                                  OpenAiAudioApi.SpeechRequest.Voice voice,
                                  OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat,
                                  OpenAiAudioApi.TtsModel ttsModel) {
        return Mono.fromCallable(() -> {
                    var audioModel = openAiClientFactory.createAudioSpeechModel(voice, audioFormat, ttsModel);

                    return audioModel.call(text);
                }).subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(ex -> {
                    log.error("TTS generation failed", ex);
                    return Mono.error(new RuntimeException("TTS generation failed: " + ex.getMessage()));
                });
    }

    private String extract(MultipartFile file) {
        try {
            return new String(file.getBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            throw new ValidationException("Can't read text file!.");
        }
    }

}
