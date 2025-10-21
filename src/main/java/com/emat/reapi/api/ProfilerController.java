package com.emat.reapi.api;

import com.emat.reapi.profiler.domain.ProfiledClientAnswerDetails;
import com.emat.reapi.profiler.domain.ProfiledClientAnswerShort;
import com.emat.reapi.profiler.domain.report.PayloadMode;
import com.emat.reapi.profiler.port.ProfileAnalysisService;
import com.emat.reapi.profiler.port.ProfiledService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api/profiler")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Profiler", description = "Endpoints for profiled client answers")
public class ProfilerController {
    private final ProfiledService profiledService;
    private final ProfileAnalysisService analysisService;

    @Operation(
            summary = "Send profiled statement to AI analyze",
            description = "Get and send profiled staement by submmision ID to AI analyze ",
            responses = @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    )
    @PostMapping(value = "/{submissionId}/analysis", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> analyze(
            @PathVariable String submissionId,
            @RequestParam(defaultValue = "false") boolean force,
            @RequestParam(defaultValue = "MINIMAL") PayloadMode mode,
            @RequestParam(defaultValue = "1") int retry
    ) {
        return profiledService.analyzeProfiledStatement(submissionId, force, mode, retry)
                .then();
    }

    @Operation(
            summary = "Get all profiled client statements",
            description = "Retrieves all profiled clients answers ",
            responses = @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    )
    @GetMapping
    public Mono<List<ProfiledClientAnswerShort>> answeredStatements() {
        //todo add sorting method from FE
        log.info("Received request: GET /api/profiler to retrieve profiled ALL clients answer");
        return profiledService.getProfiledShort(Sort.by(Sort.Direction.DESC, "submissionDate"));
    }

    @Operation(
            summary = "Get all profiled statement by submissionID",
            description = "Retrieves all profiled clients answers",
            responses = @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    )
    @GetMapping("/{submissionId}")
    public Mono<ProfiledClientAnswerDetails> answeredStatementBySubmissionId(
            @PathVariable String submissionId
    ) {
        log.info("Received request: GET /api/profiler to retrieve profiled client answer for submission ID: {}", submissionId);
        return profiledService.getClientProfiledStatement(submissionId);
    }
}
