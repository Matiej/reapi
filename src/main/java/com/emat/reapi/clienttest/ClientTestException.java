package com.emat.reapi.clienttest;

public class ClientTestException extends Throwable {
    public ClientTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientTestException(String message) {
        super(message);
    }
}
