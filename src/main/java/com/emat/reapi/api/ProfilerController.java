package com.emat.reapi.api;

import com.emat.reapi.api.dto.StatementDefinitionDto;
import com.emat.reapi.profiler.domain.StatementDefinition;
import com.emat.reapi.profiler.port.ProfilerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/profiler")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Profiler", description = "Endpoints for managing client answers and statement definitions")
public class ProfilerController {
    private final ProfilerService profilerService;

    @Operation(
            summary = "Save statement definition",
            description = "Adds a new statement definition to the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Statement definition saved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping("/statements")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StatementDefinitionDto> saveStatement(
            @Valid @RequestBody StatementDefinitionDto dto
    ) {
        log.info("Received request: POST /api/profiler/statements, statementsId: {}", dto.statementId());
        StatementDefinition domain = dto.toDomain();
        return profilerService.saveStatementDefinition(domain)
                .map(StatementDefinitionDto::toDto);
    }

    @Operation(
            summary = "Get all statement definitions",
            description = "Retrieves all statement definitions from the system",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    @GetMapping("/statements")
    public Flux<StatementDefinitionDto> getAllStatements() {
        log.info("received request: GET /api/profiler/statements");
        return profilerService.getAllStatementDefinitions()
                .map(StatementDefinitionDto::toDto);
    }

    @Operation(
            summary = "Get statement definitions by category",
            description = "Retrieves statement definitions filtered by category",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    @GetMapping(value = "/statements", params = "category")
    public Flux<StatementDefinitionDto> getStatementsByCategory(
            @Parameter(description = "Category to filter definitions")
            @RequestParam String category
    ) {
        log.info("received request: GET /api/profiler/statements with 'category': {}", category);
        return profilerService.getStatementDefinitionsByCategory(category)
                .map(StatementDefinitionDto::toDto);
    }

}
