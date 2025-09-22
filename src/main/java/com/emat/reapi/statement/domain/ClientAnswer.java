package com.emat.reapi.statement.domain;

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
    private String clientName;
    private String testName;
    private Instant date;
    private List<ClientStatement> clientStatementList;

    public ClientAnswer(String clientId, String submissionId, Instant submissionDate, String clientName, String testName, List<ClientStatement> clientStatementList) {
        this.clientId = clientId;
        this.submissionId = submissionId;
        this.submissionDate = submissionDate;
        this.clientName = clientName;
        this.testName = testName;
        this.clientStatementList = clientStatementList;
    }
}
