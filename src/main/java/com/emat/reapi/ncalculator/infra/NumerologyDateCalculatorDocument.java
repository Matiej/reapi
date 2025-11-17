package com.emat.reapi.ncalculator.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "numerology_date_calculators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumerologyDateCalculatorDocument {
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
