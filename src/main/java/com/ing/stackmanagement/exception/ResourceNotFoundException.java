package com.ing.stackmanagement.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
