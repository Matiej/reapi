package com.emat.reapi.ai;

import com.emat.reapi.ai.configuration.OpenAiParams;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class DirectTestService {
    private final OpenAiParams params;

    private WebClient testWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.openai.com/v1/audio/speech")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + params.getOpenApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .build();
    }

    public Mono<byte[]> generate(String text, String voice, String format, String model) {
        return testWebClient().post()
                .bodyValue(Map.of(
                        "input", text,
                        "voice", voice,
                        "response_format", format,
                        "model", model
                ))
                .retrieve()
                .bodyToMono(byte[].class);
    }
}
