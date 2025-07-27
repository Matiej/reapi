package com.emat.reapi.ai.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class OpenAIiConfiguration {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Bean
    public OpenAiParams getOpeApiParams() {
        return new OpenAiParams(apiKey, baseUrl);
    }

    private static ExchangeFilterFunction logRequestAndResponse() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            log.info("Request: {} {}", request.method(), request.url());
            request.headers().forEach((name, values) -> values.forEach(value ->
                    log.info("Request Header: {}={}", name, value)));
            return Mono.just(request);
        }).andThen(ExchangeFilterFunction.ofResponseProcessor(response -> {
            log.info("Response status: {}", response.statusCode());
            return response.bodyToMono(String.class)
                    .defaultIfEmpty("")
                    .doOnNext(body -> log.info("Response body: {}", body))
                    .then(Mono.just(response));
        }));
    }

    @Bean(name = "openAiWebClientBuilder")
    public WebClient.Builder openAiWebClientBuilder() {
        WebClient.Builder builder = WebClient.builder()
                .filter(logRequestAndResponse());
        return builder;
    }

    @Bean
    public RestClient.Builder openAiRestClientBuilder() {
        return RestClient.builder();
    }
}
