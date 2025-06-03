package com.emat.reapi.api.dto;

public record StationDto(
        String stationId,
        String code,
        String name,
        String latitude,
        String longitude,
        String cityId,
        String city,
        String commune,
        String district,
        String voivodeship,
        String street
) {
}
