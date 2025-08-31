package com.emat.reapi.gios.domain;

import com.emat.reapi.api.gios.GiosStationsDto;
import com.emat.reapi.api.gios.StationDto;
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
                        station.getStationId(),
                        station.getCode(),
                        station.getName(),
                        station.getLatitude(),
                        station.getLongitude(),
                        station.getCityId(),
                        station.getCity(),
                        station.getCommune(),
                        station.getDistrict(),
                        station.getVoivodeship(),
                        station.getStreet()
                )).toList();
        return new GiosStationsDto(numberOfStations, stationsDto);
    }
}
