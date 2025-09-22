package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfileCategory {
    private String categoryName;
    private String description;
}
