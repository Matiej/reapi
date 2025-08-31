package com.emat.reapi.api.dto;

import com.emat.reapi.profiler.domain.StatementCategory;
import com.emat.reapi.profiler.domain.StatementType;

public record StatementResponse (
        String statementKey,
        String statementDescription,
        Boolean status,
        String statementType
) {
}
