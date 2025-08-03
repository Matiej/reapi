package com.emat.reapi.ai;

import lombok.Getter;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class TtsRequest {

    private final boolean hd;
    private final String text;
    private final MultipartFile file;
    private final OpenAiAudioApi.SpeechRequest.Voice voice;
    private final OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat;

    public TtsRequest(String text, boolean hd,
                      OpenAiAudioApi.SpeechRequest.Voice voice,
                      OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        this.text = text;
        this.hd = hd;
        this.voice = voice;
        this.audioFormat = audioFormat;
        this.file = null;
    }

    public TtsRequest(MultipartFile file, boolean hd,
                      OpenAiAudioApi.SpeechRequest.Voice voice,
                      OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat) {
        this.file = file;
        this.hd = hd;
        this.voice = voice;
        this.audioFormat = audioFormat;
        this.text = null;
    }
}
