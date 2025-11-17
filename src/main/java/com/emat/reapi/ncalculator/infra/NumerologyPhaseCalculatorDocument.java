package com.emat.reapi.ncalculator.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "numerology_phase_calculators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumerologyPhaseCalculatorDocument {
    @Id
    private String id;
    private String requestedPhase;
    private String vowelsResult;
    private String consonantsResult;
    private String vibration;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;
    @Version
    Long version;
}
