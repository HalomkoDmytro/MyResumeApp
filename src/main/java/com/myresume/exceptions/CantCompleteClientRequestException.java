package com.myresume.exceptions;

public class CantCompleteClientRequestException extends RuntimeException {

    public CantCompleteClientRequestException(String message) {
        super(message);
    }

    public CantCompleteClientRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
