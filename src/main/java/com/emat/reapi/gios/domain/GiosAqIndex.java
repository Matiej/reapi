package com.emat.reapi.gios.domain;

import com.emat.reapi.api.dto.GiosAqIndexDto;
import com.emat.reapi.gios.infra.AqIndexDocument;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiosAqIndex {
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

    public GiosAqIndexDto toDto() {
        return new GiosAqIndexDto(
                stationId,
                calculationDate,
                indexValue,
                indexCategory,
                sourceDataDate,
                so2CalculationDate,
                so2IndexValue,
                so2IndexCategory,
                so2SourceDataDate,
                no2CalculationDate,
                no2IndexValue,
                no2IndexCategory,
                no2SourceDataDate,
                pm10CalculationDate,
                pm10IndexValue,
                pm10IndexCategory,
                pm10SourceDataDate,
                pm25CalculationDate,
                pm25IndexValue,
                pm25IndexCategory,
                pm25SourceDataDate,
                o3CalculationDate,
                o3IndexValue,
                o3IndexCategory,
                o3SourceDataDate,
                stationIndexStatus,
                criticalPollutantCode
        );
    }

    public AqIndexDocument toDocument() {
        return new AqIndexDocument(
                stationId,
                calculationDate,
                indexValue,
                indexCategory,
                sourceDataDate,
                so2CalculationDate,
                so2IndexValue,
                so2IndexCategory,
                so2SourceDataDate,
                no2CalculationDate,
                no2IndexValue,
                no2IndexCategory,
                no2SourceDataDate,
                pm10CalculationDate,
                pm10IndexValue,
                pm10IndexCategory,
                pm10SourceDataDate,
                pm25CalculationDate,
                pm25IndexValue,
                pm25IndexCategory,
                pm25SourceDataDate,
                o3CalculationDate,
                o3IndexValue,
                o3IndexCategory,
                o3SourceDataDate,
                stationIndexStatus,
                criticalPollutantCode
        );
    }
}
