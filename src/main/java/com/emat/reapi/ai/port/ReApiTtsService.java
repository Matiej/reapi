package com.emat.reapi.ai.port;

import com.emat.reapi.ai.TtsRequest;
import reactor.core.publisher.Mono;

public interface ReApiTtsService {
    Mono<byte[]>generateSpeech(TtsRequest ttsRequest);

}
