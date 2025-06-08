package com.emat.reapi.gios.integration.gios;

import com.emat.reapi.gios.domain.GiosAqIndex;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GiosAqIndexResponse {
    @JsonProperty("AqIndex")
    private AqIndexResponse aqIndex;

    public GiosAqIndex toDomain() {
        return aqIndex != null ? aqIndex.toDomain() : null;
    }

    public record AqIndexResponse(
            @JsonProperty("Identyfikator stacji pomiarowej") int stationId,
            @JsonProperty("Data wykonania obliczeń indeksu") String calculationDate,
            @JsonProperty("Wartość indeksu") int indexValue,
            @JsonProperty("Nazwa kategorii indeksu") String indexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika st") String sourceDataDate,
            @JsonProperty("Data wykonania obliczeń indeksu dla wskaźnika SO2") String so2CalculationDate,
            @JsonProperty("Wartość indeksu dla wskaźnika SO2") Integer so2IndexValue,
            @JsonProperty("Nazwa kategorii indeksu dla wskażnika SO2") String so2IndexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika SO2") String so2SourceDataDate,
            @JsonProperty("Data wykonania obliczeń indeksu dla wskaźnika NO2") String no2CalculationDate,
            @JsonProperty("Wartość indeksu dla wskaźnika NO2") Integer no2IndexValue,
            @JsonProperty("Nazwa kategorii indeksu dla wskażnika NO2") String no2IndexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika NO2") String no2SourceDataDate,
            @JsonProperty("Data wykonania obliczeń indeksu dla wskaźnika PM10") String pm10CalculationDate,
            @JsonProperty("Wartość indeksu dla wskaźnika PM10") Integer pm10IndexValue,
            @JsonProperty("Nazwa kategorii indeksu dla wskażnika PM10") String pm10IndexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika PM10") String pm10SourceDataDate,
            @JsonProperty("Data wykonania obliczeń indeksu dla wskaźnika PM2.5") String pm25CalculationDate,
            @JsonProperty("Wartość indeksu dla wskaźnika PM2.5") Integer pm25IndexValue,
            @JsonProperty("Nazwa kategorii indeksu dla wskażnika PM2.5") String pm25IndexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika PM2.5") String pm25SourceDataDate,
            @JsonProperty("Data wykonania obliczeń indeksu dla wskaźnika O3") String o3CalculationDate,
            @JsonProperty("Wartość indeksu dla wskaźnika O3") Integer o3IndexValue,
            @JsonProperty("Nazwa kategorii indeksu dla wskażnika O3") String o3IndexCategory,
            @JsonProperty("Data danych źródłowych, z których policzono wartość indeksu dla wskaźnika O3") String o3SourceDataDate,
            @JsonProperty("Status indeksu ogólnego dla stacji pomiarowej") boolean stationIndexStatus,
            @JsonProperty("Kod zanieczyszczenia krytycznego") String criticalPollutantCode
    ) {
        public GiosAqIndex toDomain() {
            return new GiosAqIndex(
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
}
