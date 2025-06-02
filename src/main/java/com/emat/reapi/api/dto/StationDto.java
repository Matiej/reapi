package com.emat.reapi.api.dto;

public record StationDto(
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
