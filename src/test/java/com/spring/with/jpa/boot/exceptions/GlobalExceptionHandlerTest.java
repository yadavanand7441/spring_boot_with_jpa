package com.spring.with.jpa.boot.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testNotFoundExceptionHandler() {
        // Arrange
        ResourceNotFoundException ex = new ResourceNotFoundException("Employee not found with given id");

        // Act
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.notFoundException(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getBody().get("status"));
        assertEquals("Employee not found with given id", response.getBody().get("message"));
    }
}
