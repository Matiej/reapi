package com.emat.reapi.api;

import com.emat.reapi.api.dto.clienttestdto.ClientTestDto;
import com.emat.reapi.api.dto.fptestdto.FpTestResponse;
import com.emat.reapi.clienttest.ClientTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client/test")
@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Client Test", description = "Endpoints for managing client profile testes.")
public class ClientTestController {
    private final ClientTestService clientTestService;

    @GetMapping("/{publicToken}")
    @Operation(
            summary = "Get client test",
            description = "Retrieves client test by public token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Test found and prepared successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid public token ")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientTestDto> getAllTests(
            @PathVariable String publicToken
    ) {
        log.info("Received request: GET '/api/pftest' to retrieve all tests");
        return clientTestService.getClientTestByToken(publicToken)
                .map(ClientTestDto::fromDomain);
    }
}
