package com.emat.reapi.ai.port;

import org.springframework.ai.openai.api.OpenAiAudioApi;
import reactor.core.publisher.Mono;

public interface ReApiTtsService {
    Mono<byte[]> generateBasicSpeech(
            String text,
            OpenAiAudioApi.SpeechRequest.Voice voice,
            OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat
            );

    Mono<byte[]> generateProSpeech(
            String text,
            OpenAiAudioApi.SpeechRequest.Voice voice,
            OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat
    );
}
