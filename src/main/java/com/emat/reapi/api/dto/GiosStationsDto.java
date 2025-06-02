package com.emat.reapi.api.dto;

import java.util.List;

public record GiosStationsDto(
        int numberOfStations,
        List<StationDto> stationDtoList
) {

}
