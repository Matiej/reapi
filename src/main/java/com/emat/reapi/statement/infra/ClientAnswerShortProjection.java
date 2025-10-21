package com.emat.reapi.statement.infra;

import java.time.Instant;

public interface ClientAnswerShortProjection {
    String getClientName();
    String getClientId();
    String getSubmissionId();
    Instant getSubmissionDate();
    String getTestName();
}
