package com.emat.reapi.api.dto.InsightReportDto;

import com.emat.reapi.profiler.domain.report.RecommendedIntervention;

import java.util.List;

public record CategoryInsightDto(
        String categoryId,
        String categoryLabelPl,
        List<String>strengthsPl,
        List<String> risksPl,
        List<String> contradictionsPl,
        List<RecommendedInterventionDto> recommendedInterventionsDto
) {
}
