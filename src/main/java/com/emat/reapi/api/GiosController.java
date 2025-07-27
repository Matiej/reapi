package com.emat.reapi.api;

import com.emat.reapi.api.dto.AqIndexDto;
import com.emat.reapi.api.dto.GiosAqIndexDto;
import com.emat.reapi.api.dto.GiosStationsDto;
import com.emat.reapi.gios.domain.GiosAqIndex;
import com.emat.reapi.gios.domain.GiosStations;
import com.emat.reapi.gios.infra.AqIndexDocument;
import com.emat.reapi.gios.port.GiosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gios")
@Slf4j
@AllArgsConstructor
public class GiosController {
    private final GiosService giosService;

    @Operation(
            summary = "Synchronize GIOS monitoring stations",
            description = "Fetches and stores a list of GIOS air quality monitoring stations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stations successfully synchronized",
                    content = @Content(schema = @Schema(implementation = GiosStationsDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stations/synchronise")
    Mono<GiosStationsDto> synchroniseStations() {
        log.info("GET /gios/stations/synchronise");
        return giosService.synchronizeStations().map(GiosStations::toDto);
    }

    @Operation(
            summary = "Get Air Quality Index (AQI) by station ID",
            description = "Returns the air quality index for a specific GIOS station."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AQI data returned successfully",
                    content = @Content(schema = @Schema(implementation = GiosAqIndexDto.class))),
            @ApiResponse(responseCode = "404", description = "AQI data not found for the given station ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/aqindex")
    Mono<GiosAqIndexDto> getAqIndex(@RequestParam String stationId) {
        log.info("GET /gios/aqindex for stationId: {} called", stationId);
        return giosService.getAqIndex(stationId).map(GiosAqIndex::toDto);
    }

    @Operation(
            summary = "Synchronize Air Quality Indexes (AQI) for all stations",
            description = "Fetches and stores AQI data for all available GIOS stations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AQI data successfully synchronized",
                    content = @Content(schema = @Schema(implementation = AqIndexDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/aqindex/synchronise")
    @ResponseStatus(HttpStatus.OK)
    Flux<AqIndexDto> synchroniseAqIndex() {
        log.info("GET /aqindex/synchronise called");
        return giosService.saveMeasurementsForAllStations().map(AqIndexDocument::toDto);
    }
}
