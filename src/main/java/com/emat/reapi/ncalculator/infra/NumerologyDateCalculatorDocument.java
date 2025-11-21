package com.emat.reapi.ncalculator.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = NumerologyDateCalculatorDocument.COLLECTION_NAME)
@TypeAlias(value = NumerologyDateCalculatorDocument.COLLECTION_NAME)
public class NumerologyDateCalculatorDocument {

    public static final String COLLECTION_NAME = "numerology_date_calculators";

    @Id
    private String id;
    private String requestedBrithDate;
    private String requestedReferenceDate;
    private String mainVibration;
    private int personalYear;
    private int yearOfGlobalEnergy;
    private int numerologyYear;
    private int personalMonth;
    private int worldDayVibration;
    private int personalDay;
}

