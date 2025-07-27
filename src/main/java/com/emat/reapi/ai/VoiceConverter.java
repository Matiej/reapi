package com.emat.reapi.ai;

import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VoiceConverter implements Converter<String, OpenAiAudioApi.SpeechRequest.Voice> {
    @Override
    public OpenAiAudioApi.SpeechRequest.Voice convert(String source) {
        for (OpenAiAudioApi.SpeechRequest.Voice voice : OpenAiAudioApi.SpeechRequest.Voice.values()) {
            if (voice.value.equalsIgnoreCase(source.trim())) {
                return voice;
            }
        }
        throw new IllegalArgumentException("Invalid voice: " + source);
    }
}
