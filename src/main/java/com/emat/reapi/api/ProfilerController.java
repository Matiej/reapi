package com.emat.reapi.api;

import com.emat.reapi.api.dto.ClientAnswerResponse;
import com.emat.reapi.profiler.port.ProfilerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/profiler")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Profiler", description = "Endpoints for managing client answers")
public class ProfilerController {
    private final ProfilerService profilerService;


    @Operation(
            summary = "Get all answered client statements",
            description = "Retrieves all answered client questions with public id and date",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    @GetMapping()
    public Mono<List<ClientAnswerResponse>> answeredStatements() {
        log.info("Received request: GET /api/profiler to retrieve all client answered statements");
        return profilerService.getAllAnsweredStatements()
                .map(d -> d.stream()
                        .map(ClientAnswerResponse::fromDomain)
                        .toList()
                );
    }


}
