package com.emat.reapi.profiler.domain;

import lombok.Getter;

@Getter
public enum StatementType {
    LIMITING("Ograniczające"), SUPPORTING("Wspierające");

    private final String plDescription;

    StatementType(String plDescription) {
        this.plDescription = plDescription;
    }

}
