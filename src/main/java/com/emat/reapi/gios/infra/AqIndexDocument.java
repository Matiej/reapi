package com.emat.reapi.gios.infra;

import com.emat.reapi.api.gios.AqIndexDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "aqindex")
public class AqIndexDocument {
    @Id
    private String id;
    private String savingDate;
    private String stationId;
    private String calculationDate;
    private int indexValue;
    private String indexCategory;
    private String sourceDataDate;
    private String so2CalculationDate;
    private Integer so2IndexValue;
    private String so2IndexCategory;
    private String so2SourceDataDate;
    private String no2CalculationDate;
    private Integer no2IndexValue;
    private String no2IndexCategory;
    private String no2SourceDataDate;
    private String pm10CalculationDate;
    private Integer pm10IndexValue;
    private String pm10IndexCategory;
    private String pm10SourceDataDate;
    private String pm25CalculationDate;
    private Integer pm25IndexValue;
    private String pm25IndexCategory;
    private String pm25SourceDataDate;
    private String o3CalculationDate;
    private Integer o3IndexValue;
    private String o3IndexCategory;
    private String o3SourceDataDate;
    private boolean stationIndexStatus;
    private String criticalPollutantCode;

    public AqIndexDocument(String stationId, String savingDate, String calculationDate, int indexValue, String indexCategory, String sourceDataDate, String so2CalculationDate, Integer so2IndexValue, String so2IndexCategory, String so2SourceDataDate, String no2CalculationDate, Integer no2IndexValue, String no2IndexCategory, String no2SourceDataDate, String pm10CalculationDate, Integer pm10IndexValue, String pm10IndexCategory, String pm10SourceDataDate, String pm25CalculationDate, Integer pm25IndexValue, String pm25IndexCategory, String pm25SourceDataDate, String o3CalculationDate, Integer o3IndexValue, String o3IndexCategory, String o3SourceDataDate, boolean stationIndexStatus, String criticalPollutantCode) {
        this.stationId = stationId;
        this.savingDate = savingDate;
        this.calculationDate = calculationDate;
        this.indexValue = indexValue;
        this.indexCategory = indexCategory;
        this.sourceDataDate = sourceDataDate;
        this.so2CalculationDate = so2CalculationDate;
        this.so2IndexValue = so2IndexValue;
        this.so2IndexCategory = so2IndexCategory;
        this.so2SourceDataDate = so2SourceDataDate;
        this.no2CalculationDate = no2CalculationDate;
        this.no2IndexValue = no2IndexValue;
        this.no2IndexCategory = no2IndexCategory;
        this.no2SourceDataDate = no2SourceDataDate;
        this.pm10CalculationDate = pm10CalculationDate;
        this.pm10IndexValue = pm10IndexValue;
        this.pm10IndexCategory = pm10IndexCategory;
        this.pm10SourceDataDate = pm10SourceDataDate;
        this.pm25CalculationDate = pm25CalculationDate;
        this.pm25IndexValue = pm25IndexValue;
        this.pm25IndexCategory = pm25IndexCategory;
        this.pm25SourceDataDate = pm25SourceDataDate;
        this.o3CalculationDate = o3CalculationDate;
        this.o3IndexValue = o3IndexValue;
        this.o3IndexCategory = o3IndexCategory;
        this.o3SourceDataDate = o3SourceDataDate;
        this.stationIndexStatus = stationIndexStatus;
        this.criticalPollutantCode = criticalPollutantCode;
    }

    public AqIndexDto toDto() {
        return new AqIndexDto(
                this.id,
                this.savingDate,
                this.stationId,
                this.calculationDate,
                this.indexValue,
                this.indexCategory,
                this.sourceDataDate,
                this.so2CalculationDate,
                this.so2IndexValue,
                this.so2IndexCategory,
                this.so2SourceDataDate,
                this.no2CalculationDate,
                this.no2IndexValue,
                this.no2IndexCategory,
                this.no2SourceDataDate,
                this.pm10CalculationDate,
                this.pm10IndexValue,
                this.pm10IndexCategory,
                this.pm10SourceDataDate,
                this.pm25CalculationDate,
                this.pm25IndexValue,
                this.pm25IndexCategory,
                this.pm25SourceDataDate,
                this.o3CalculationDate,
                this.o3IndexValue,
                this.o3IndexCategory,
                this.o3SourceDataDate,
                this.stationIndexStatus,
                this.criticalPollutantCode
        );
    }

}
