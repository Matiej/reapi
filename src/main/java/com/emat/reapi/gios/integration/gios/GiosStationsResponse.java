package com.emat.reapi.gios.integration.gios;

import com.emat.reapi.gios.domain.GiosStations;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class GiosStationsResponse {
    @JsonProperty("Lista stacji pomiarowych")
    private List<StationResponse> stationsResponse;

    public int getNumberOfStations() {
        return Optional.ofNullable(stationsResponse)
                .map(List::size)
                .orElse(0);
    }

    public GiosStations toDomain() {
        var stations = stationsResponse.stream()
                .map(station -> new GiosStations.Station(
                        station.id,
                        station.code,
                        station.name,
                        station.latitude,
                        station.longitude,
                        station.cityId,
                        station.city,
                        station.commune,
                        station.district,
                        station.voivodeship,
                        station.street
                )).toList();
        return new GiosStations(stations, getNumberOfStations());
    }

    public static record StationResponse(
            @JsonProperty("Identyfikator stacji") Long id,
            @JsonProperty("Kod stacji") String code,
            @JsonProperty("Nazwa stacji") String name,
            @JsonProperty("WGS84 φ N") String latitude,
            @JsonProperty("WGS84 λ E") String longitude,
            @JsonProperty("Identyfikator miasta") Long cityId,
            @JsonProperty("Nazwa miasta") String city,
            @JsonProperty("Gmina") String commune,
            @JsonProperty("Powiat") String district,
            @JsonProperty("Województwo") String voivodeship,
            @JsonProperty("Ulica") String street
    ) {
    }
}
