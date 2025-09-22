package com.emat.reapi.profiler.domain;

import com.emat.reapi.statement.domain.StatementType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfiledStatement {
    private String description;
    private StatementType type;
    private Boolean status;
}
