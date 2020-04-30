package com.myresume.exceptions;

public class ProfileNotFound extends RuntimeException {

    public ProfileNotFound(String message) {
        super(message);
    }
}
