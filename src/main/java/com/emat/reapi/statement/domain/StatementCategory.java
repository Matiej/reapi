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

    private final String plName;

    StatementCategory(String plDescription) {
        this.plName = plDescription;
    }

    public String getPlName() {
        return plName;
    }

    public static StatementCategory fromPlDescription(String value) {
        if (value == null) return null;
        return Arrays.stream(values())
                .filter(v -> v.plName.trim().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown Polish description: " + value));
    }
}
