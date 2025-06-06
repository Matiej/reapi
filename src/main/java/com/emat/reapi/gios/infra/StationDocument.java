package com.emat.reapi.gios.infra;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDocument that = (StationDocument) o;
        return Objects.equals(stationId, that.stationId) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(cityId, that.cityId) && Objects.equals(city, that.city) && Objects.equals(commune, that.commune) && Objects.equals(district, that.district) && Objects.equals(voivodeship, that.voivodeship) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationId, code, name, latitude, longitude, cityId, city, commune, district, voivodeship, street);
    }
}
