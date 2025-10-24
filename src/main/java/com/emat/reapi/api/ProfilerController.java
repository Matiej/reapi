package com.emat.reapi.api;

import com.emat.reapi.profiler.domain.ProfiledClientAnswerDetails;
import com.emat.reapi.profiler.domain.ProfiledClientAnswerShort;
import com.emat.reapi.profiler.port.ProfiledService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Operation(
            summary = "Get all profiled all client short statements",
            description = "Retrieves all profiled clients answers. List of short answers for UI list view.",
            responses = @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    )
    @GetMapping
    public Mono<List<ProfiledClientAnswerShort>> answeredStatements() {
        log.info("Received request: GET /api/profiler to retrieve profiled ALL clients answer");
        return profiledService.getProfiledShort(Sort.by(Sort.Direction.DESC, "submissionDate"));
    }

    @Operation(
            summary = "Get all profiled detail statements by submissionID",
            description = "Retrieves all profiled clients answers. List of profiled statements with all of details",
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
