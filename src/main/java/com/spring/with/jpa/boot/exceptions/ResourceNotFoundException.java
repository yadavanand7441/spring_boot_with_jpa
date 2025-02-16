package com.spring.with.jpa.boot.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("employee is not presented with given id !");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
