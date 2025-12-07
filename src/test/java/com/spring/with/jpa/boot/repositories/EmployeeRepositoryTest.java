package com.spring.with.jpa.boot.repositories;

import com.spring.with.jpa.boot.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAndFindById() {
        Employee emp = new Employee("E001", "Anand", 50000.0, "Kolkata");

        // mock behavior
        when(employeeRepository.save(emp)).thenReturn(emp);
        when(employeeRepository.findById("E001")).thenReturn(Optional.of(emp));

        // verify
        Employee saved = employeeRepository.save(emp);
        assertTrue(employeeRepository.findById("E001").isPresent());
        assertTrue(saved.getName().equals("Anand"));
    }
}
