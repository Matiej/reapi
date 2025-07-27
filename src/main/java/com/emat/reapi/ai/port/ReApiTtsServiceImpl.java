package com.emat.reapi.ai.port;

import com.emat.reapi.ai.integration.OpenAiClientFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
@RequiredArgsConstructor
class ReApiTtsServiceImpl implements ReApiTtsService {

    private final OpenAiClientFactory openAiClientFactory;

    @Override
    public Mono<byte[]> generateBasicSpeech(String text,
                                            OpenAiAudioApi.SpeechRequest.Voice voice,
                                            OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        log.info("Generating TTS basic audio for text: {}", text);
        return generateSpeech(text, voice, audioFormat, OpenAiAudioApi.TtsModel.TTS_1);
    }

    @Override
    public Mono<byte[]> generateProSpeech(String text, OpenAiAudioApi.SpeechRequest.Voice voice, OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        log.info("Generating TTS pro HD audio for text: {}", text);
        return generateSpeech(text, voice, audioFormat, OpenAiAudioApi.TtsModel.TTS_1_HD);
    }

    private Mono<byte[]> generateSpeech(String text,
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
}
