package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientAnswer {
    private String clientId;
    private String submissionId;
    private String name;
    private String testName;
    private List<ClientStatement> clientStatementList;
}
