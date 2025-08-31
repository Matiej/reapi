package com.emat.reapi.api.gios;

import java.util.List;

public record GiosStationsDto(
        int numberOfStations,
        List<StationDto> stationDtoList
) {

}
