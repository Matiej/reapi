package com.emat.reapi.submission;

import lombok.Getter;

@Getter
public class SubmissionException extends Throwable {
    private final SubmissionErrorType type;

    public SubmissionException(String message, Throwable e, SubmissionErrorType errorType) {
        super(message);
        this.type = errorType;
    }

    public SubmissionException(String message, SubmissionErrorType errorType) {
        super(message);
        this.type = errorType;
    }

    public enum SubmissionErrorType {
        SUBMISSION_CREATE_ERROR,
        SUBMISSION_UPDATE_ERROR,
        SUBMISSION_NOT_FOUND
    }
}


