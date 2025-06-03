package com.emat.reapi.gios.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "station")
public class StationDocument {
    @Id
    private String id;
    private String stationId;
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
