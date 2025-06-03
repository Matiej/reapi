package com.emat.reapi.gios.domain;

import com.emat.reapi.gios.infra.StationDocument;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Station {
    private String stationId;
    private String code;
    private String name;
    private String latitude;
    private String longitude;
    private String cityId;
    private String city;
    private String commune;
    private String district;
    private String voivodeship;
    private String street;

    public StationDocument toDocument() {
        return new StationDocument(
                stationId,
                code,
                name,
                latitude,
                longitude,
                cityId,
                city,
                commune,
                district,
                voivodeship,
                street
        );
    }
}
