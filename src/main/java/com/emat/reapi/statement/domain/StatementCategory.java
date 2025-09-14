package com.emat.reapi.statement.domain;

import java.util.Arrays;

public enum StatementCategory {

    SCARCITY_GUARDIAN("Strażniczka Braku"),
    SELF_SUFFICIENT_SHIELD("Samowystarczalna Tarcza"),
    FROZEN_VISIONARY("Zamrożona Wizjonerka"),
    LOYAL_HEIRESS("Lojalna Dziedziczka"),
    WITHDRAWN_LEADER("Wycofana Liderka"),
    OVERWORKED_PERFECTIONIST("Zapracowana Perfekcjonistka"),
    BLOCKED_IN_RECEIVING("Zatrzymana w Przyjmowaniu"),
    MODESTY_IDEALIST("Idealistka Skromności");

    private final String plDescription;

    StatementCategory(String plDescription) {
        this.plDescription = plDescription;
    }

    public String getPlDescription() {
        return plDescription;
    }

    public static StatementCategory fromPlDescription(String value) {
        if (value == null) return null;
        return Arrays.stream(values())
                .filter(v -> v.plDescription.trim().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown Polish description: " + value));
    }
}
