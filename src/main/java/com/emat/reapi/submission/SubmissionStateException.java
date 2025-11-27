package com.emat.reapi.submission;

public class SubmissionStateException extends SubmissionException{
    public SubmissionStateException(String message, Throwable cause, SubmissionErrorType errorType) {
        super(message, cause, errorType);
    }

    public SubmissionStateException(String message, SubmissionErrorType errorType) {
        super(message, errorType);
    }
}
