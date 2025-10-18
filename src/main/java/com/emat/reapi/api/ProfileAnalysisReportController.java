package com.emat.reapi.api;


import com.emat.reapi.api.dto.InsightReportDto.InsightReportDto;
import com.emat.reapi.api.dto.InsightReportDto.InsightReportMapper;
import com.emat.reapi.profiler.port.ProfileAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
@Slf4j
@AllArgsConstructor
@Tag(name = "Profiler analysis", description = "Endpoints for AI profile analysis")
public class ProfileAnalysisReportController {
    private final ProfileAnalysisService profileAnalysisService;


    @Operation(
            summary = "Get all AI analysis by submissionID",
            description = "Retrieves all profiled clients analysis",
            responses = @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    )
    @GetMapping("/{submissionId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<InsightReportDto>> findProfiledClientAnalysisBySubmissionId(@PathVariable String submissionId) {
        log.info("Received request: GET /api/analysis to retrieve profiled client reports for submission ID: {}", submissionId);
        return profileAnalysisService.getAnalysis(submissionId)
                .map(results -> results
                        .stream()
                        .map(InsightReportMapper::toDto)
                        .toList());
    }
}
