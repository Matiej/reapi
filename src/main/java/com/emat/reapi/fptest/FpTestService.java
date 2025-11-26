package com.emat.reapi.fptest;

import com.emat.reapi.api.dto.fptestdto.FpTestDto;
import com.emat.reapi.fptest.domain.FpTest;
import com.emat.reapi.fptest.domain.FpTestStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FpTestService {
    Mono<FpTest> createFpTest(FpTestDto fpTestDto);
    Mono<FpTest> updateFpTest(FpTestDto fpTestDto);
    Flux<FpTest> findAll();
    Mono<FpTest> findFpTestByTestId(String testId);
    Mono<Void> deleteFpTestByTestId(String testId);
    Flux<FpTestStatement> getAllTestStatements();
}
