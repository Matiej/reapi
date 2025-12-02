package com.emat.reapi.profileanalysis.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InterventionType {
    RYTUAL("rytuał"),
    CWICZENIE("ćwiczenie"),
    NAWYK("nawyk"),
    ZADANIE_LICZBOWE("zadanie liczbowe"),
    PRACA_Z_PRZEKONANIEM("praca z przekonaniem");

    private final String jsonValue;

    InterventionType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
