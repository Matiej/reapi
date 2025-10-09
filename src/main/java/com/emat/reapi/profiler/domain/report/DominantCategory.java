package com.emat.reapi.profiler.domain.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DominantCategory {
    private String categoryId;
    private double balanceIndex;
    private String why;
}
