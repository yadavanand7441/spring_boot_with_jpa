package com.spring.with.jpa.boot.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testDefaultConstructorMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException();
        assertEquals("employee is not presented with given id !", ex.getMessage());
    }

    @Test
    void testParameterizedConstructorMessage() {
        String customMessage = "Employee not found in DB with id E001";
        ResourceNotFoundException ex = new ResourceNotFoundException(customMessage);
        assertEquals(customMessage, ex.getMessage());
    }

    @Test
    void testExceptionIsInstanceOfRuntimeException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Some message");
        assertTrue(ex instanceof RuntimeException);
    }
}
