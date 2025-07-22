package com.emat.reapi.api;

import com.emat.reapi.api.dto.GiosAqIndexDto;
import com.emat.reapi.api.dto.GiosStationsDto;
import com.emat.reapi.gios.domain.GiosAqIndex;
import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.port.GiosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gios")
@Slf4j
@AllArgsConstructor
public class GiosController {
    private final GiosService giosService;

    @GetMapping("/stations/synchronise")
    ResponseEntity<Mono<GiosStationsDto>> synchroniseStations() {
        log.info("GET /gios/stations/synchronise");
        Mono<GiosStationsDto> allStations = giosService.synchronizeStations().map(GiosStations::toDto);
        return ResponseEntity
                .status(200)
                .body(allStations);
    }

    @GetMapping("/aqindex")
    ResponseEntity<Mono<GiosAqIndexDto>> getAqIndex(@RequestParam String stationId) {
        log.info("GET /gios/aqindex for stationId: {} called", stationId);
        Mono<GiosAqIndexDto> aqIndex = giosService.getAqIndex(stationId).map(GiosAqIndex::toDto);
        return ResponseEntity
                .status(200)
                .body(aqIndex);
    }
}
