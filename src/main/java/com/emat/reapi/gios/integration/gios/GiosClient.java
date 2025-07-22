package com.emat.reapi.gios.integration.gios;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class GiosClient {
    private static final String GET_ALL_STATIONS_URI = "/v1/rest/station/findAll";
    private static final String AQ_INDEX_URI = "/v1/rest/aqindex/getIndex/{stationId}";
    private static final String MAX_STATIONS_SIZE = "500";
    private final WebClient giosWebClient;

    public Mono<GiosStationsResponse> getAllStations() {
        return giosWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GET_ALL_STATIONS_URI)
                        .queryParam("size", MAX_STATIONS_SIZE)
                        .build()
                )
                .retrieve()
                .bodyToMono(GiosStationsResponse.class)
                .doOnNext(sub -> log.info("Calling GIOS: {}", GET_ALL_STATIONS_URI))
                .doOnError(e -> log.error("Error calling GIOS API", e));
    }

    public Mono<GiosAqIndexResponse> getAqIndex(String stationId) {
        return giosWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(AQ_INDEX_URI)
                        .build(stationId)
                )
                .retrieve()
                .bodyToMono(GiosAqIndexResponse.class)
                .doOnNext(response -> log.info("Calling on GIOS aqindex: {}", AQ_INDEX_URI))
                .doOnError(e -> log.error("Error calling GIOS API", e));
    }
}
