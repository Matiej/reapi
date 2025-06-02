package com.emat.reapi.gios.integration.gios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GiosWebClientConfig {
    private static final String GIOS_API_BASE_URL = "https://api.gios.gov.pl/pjp-api";

    @Bean
    WebClient giosWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(GIOS_API_BASE_URL)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(giosDefaultHeaders()))
                .build();
    }

    private HttpHeaders giosDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/ld+json");
        return new HttpHeaders(headers);
    }
}
