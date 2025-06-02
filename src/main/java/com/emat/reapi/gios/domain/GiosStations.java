package com.emat.reapi.gios.domain;

import com.emat.reapi.api.dto.GiosStationsDto;
import com.emat.reapi.api.dto.StationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GiosStations {
    private List<Station> stationList;
    private int numberOfStations;

    public GiosStationsDto toDto() {
        var stationsDto = stationList.stream()
                .map(station -> new StationDto(
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
        return new GiosStationsDto(numberOfStations, stationsDto);
    }

    public static record Station(
            Long id,
            String code,
            String name,
            String latitude,
            String longitude,
            Long cityId,
            String city,
            String commune,
            String district,
            String voivodeship,
            String street
    ) {
    }
}
