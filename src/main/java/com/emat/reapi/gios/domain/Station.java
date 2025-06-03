package com.emat.reapi.gios.domain;

import lombok.Data;

@Data
public class Station {
    private Long stationId;
    private String code;
    private String name;
    private String latitude;
    private String longitude;
    private Long cityId;
    private String city;
    private String commune;
    private String district;
    private String voivodeship;
    private String street;
}
