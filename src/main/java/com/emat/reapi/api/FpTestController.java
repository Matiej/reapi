package com.emat.reapi.api;

import com.emat.reapi.api.dto.fptestdto.FpTestDto;
import com.emat.reapi.api.dto.fptestdto.FpTestResponse;
import com.emat.reapi.api.dto.fptestdto.FpTestStatementDto;
import com.emat.reapi.fptest.FpTestService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/pftest")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Profiler Test", description = "Endpoints for managing Finance profile tests.")
public class FpTestController {
    private final FpTestService fpTestService;

    @GetMapping
    @Operation(
            summary = "Get all tests",
            description = "Retrieves all available tests",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    @ResponseStatus(HttpStatus.OK)
    public Flux<FpTestResponse> getAllTests() {
        log.info("Received request: GET '/api/pftest' to retrieve all tests");
        return fpTestService.findAll()
                .map(FpTestResponse::toResponse);
    }

    @GetMapping("/{testId}")
    @Operation(
            summary = "Get test by testId",
            description = "Retrieves all available tests",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    public Mono<FpTestResponse> getTestByTestId(@PathVariable String testId) {
        log.info("Received request: GET '/api/pftest/{testId}' to retrieve test for given testId");
        return fpTestService.findFpTestByTestId(testId)
                .map(FpTestResponse::toResponse);
    }

    @Operation(
            summary = "Create new test",
            description = "Creates a new test t",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Test created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request payload")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FpTestResponse> createTest(@Valid @RequestBody FpTestDto request) {
        log.info("Received request: POST '/api/pftest/' creating test");
        return fpTestService.createFpTest(request)
                .map(FpTestResponse::toResponse);
    }

    @PutMapping("/{testId}")
    @Operation(
            summary = "Update test by testId",
            description = "Update test by given testId",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Test updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request payload")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public Mono<FpTestResponse> updateTest(
            @PathVariable String testId,
            @Valid @RequestBody FpTestDto request
    ) {
        log.info("Received request: PUT '/api/pftest/{testId}' updating test");
        return fpTestService.updateFpTest(request.addTestId(testId))
                .map(FpTestResponse::toResponse);
    }

    @DeleteMapping("/{testId}")
    @Operation(
            summary = "Delete test by testId",
            description = "Delete test by given testId",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Test Delete successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request payload")
            }
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteTest(@PathVariable String testId) {
        return fpTestService.deleteFpTestByTestId(testId);
    }

    @GetMapping("/statements")
    @Operation(
            summary = "Get all test statements",
            description = "Get all test shorten statements for creation and test managing",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Fetched successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request payload")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public Flux<FpTestStatementDto> getAllTestStatements() {
        log.info("Received request: GET '/api/pftest/statements}' to get all shroten test statements");
        return fpTestService.getAllTestStatements()
                .map(FpTestStatementDto::fromDomain);
    }
}
