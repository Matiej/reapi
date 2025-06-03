package com.emat.reapi.gios.infra;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "station")
public class StationDocument {
    @Id
    private String id;
    @Indexed(unique = true)
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

    public StationDocument(String stationId, String code, String name, String latitude, String longitude, String cityId, String city, String commune, String district, String voivodeship, String street) {
        this.stationId = stationId;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityId = cityId;
        this.city = city;
        this.commune = commune;
        this.district = district;
        this.voivodeship = voivodeship;
        this.street = street;
    }
}
