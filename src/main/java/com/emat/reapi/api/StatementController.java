package com.emat.reapi.api;

import com.emat.reapi.api.dto.ClientAnswerResponse;
import com.emat.reapi.clienttalytest.ClientAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/statement")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Statement", description = "Endpoints for managing client answers")
public class StatementController {
    private final ClientAnswerService clientAnswerService;


    @Operation(
            summary = "Get all answered client statements",
            description = "Retrieves all answered client questions with public id and date",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    @GetMapping()
    public Mono<List<ClientAnswerResponse>> answeredStatements() {
        log.info("Received request: GET /api/statement to retrieve all client answered statements");
        return clientAnswerService.getAllAnsweredStatements()
                .map(d -> d.stream()
                        .map(ClientAnswerResponse::fromDomain)
                        .toList()
                );
    }

    @GetMapping("/submission/{submissionId}")
    public Mono<ClientAnswerResponse> answeredStatement(@PathVariable String submissionId) {
        log.info("Received request: GET /api/statement/submission/{submissionId} answered statements fo submission: {}", submissionId);
        return clientAnswerService.getAnsweredStatementBySubmissionId(submissionId)
                .map(ClientAnswerResponse::fromDomain);
    }
}
