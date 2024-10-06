package com.hibernateapp.exception;

public class ResourceNotFoundException extends Exception {

    private String message;

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    

}
