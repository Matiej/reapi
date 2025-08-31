package com.emat.reapi.profiler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class ClientAnswer {
    private String clientId;
    private String publicId;
    private String submissionId;
    private Instant submissionDate;
    private String name;
    private String testName;
    private Instant date;
    private List<ClientStatement> clientStatementList;

    public ClientAnswer(String clientId, String submissionId, Instant submissionDate, String name, String testName, List<ClientStatement> clientStatementList) {
        this.clientId = clientId;
        this.submissionId = submissionId;
        this.submissionDate = submissionDate;
        this.name = name;
        this.testName = testName;
        this.clientStatementList = clientStatementList;
    }
}
