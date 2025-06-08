package com.emat.reapi.api.dto;

public record GiosAqIndexDto(
        int stationId,
        String calculationDate,
        int indexValue,
        String indexCategory,
        String sourceDataDate,
        String so2CalculationDate,
        Integer so2IndexValue,
        String so2IndexCategory,
        String so2SourceDataDate,
        String no2CalculationDate,
        Integer no2IndexValue,
        String no2IndexCategory,
        String no2SourceDataDate,
        String pm10CalculationDate,
        Integer pm10IndexValue,
        String pm10IndexCategory,
        String pm10SourceDataDate,
        String pm25CalculationDate,
        Integer pm25IndexValue,
        String pm25IndexCategory,
        String pm25SourceDataDate,
        String o3CalculationDate,
        Integer o3IndexValue,
        String o3IndexCategory,
        String o3SourceDataDate,
        boolean stationIndexStatus,
        String criticalPollutantCode
) {}

