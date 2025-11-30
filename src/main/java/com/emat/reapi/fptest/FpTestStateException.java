package com.emat.reapi.fptest;

public class FpTestStateException extends RuntimeException {
    private final FpTestStateException.FpTestErrorType type;

    public FpTestStateException(String message, Throwable cause, FpTestErrorType type) {
        super(message, cause);
        this.type = type;
    }

    public FpTestStateException(String message, FpTestErrorType type) {
        super(message);
        this.type = type;
    }

    public FpTestErrorType getType() {
        return type;
    }

    public enum FpTestErrorType {
        FP_TEST_EDIT_ERROR,
        FP_TEST_DELETE_ERROR
    }
}
