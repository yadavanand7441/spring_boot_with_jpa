package com.spring.with.jpa.boot.controller;

import com.spring.with.jpa.boot.entity.Employee;
import com.spring.with.jpa.boot.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        emp1 = new Employee("E001", "Anand", 50000.0, "Kolkata");
        emp2 = new Employee("E002", "Rahul", 60000.0, "Delhi");
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.createEmployee(emp1)).thenReturn(emp1);

        ResponseEntity<Employee> response = employeeController.createEmployee(emp1);

        assertEquals("Anand", response.getBody().getName());
        verify(employeeService, times(1)).createEmployee(emp1);
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById("E001")).thenReturn(emp1);

        ResponseEntity<Employee> response = employeeController.getEmployeeById("E001");

        assertEquals("E001", response.getBody().getId());
        verify(employeeService, times(1)).getEmployeeById("E001");
    }

    @Test
    void testUpdateEmployeeById() {
        when(employeeService.updateEmployeeById(emp1, "E001")).thenReturn(emp1);

        ResponseEntity<Employee> response = employeeController.updateEmployeeById("E001", emp1);

        assertEquals("Anand", response.getBody().getName());
        verify(employeeService, times(1)).updateEmployeeById(emp1, "E001");
    }

    @Test
    void testDeleteEmployeeById() {
        when(employeeService.deleteEmployeeById("E001")).thenReturn(emp1);

        ResponseEntity<Employee> response = employeeController.deleteEmployeeById("E001");

        assertEquals("E001", response.getBody().getId());
        verify(employeeService, times(1)).deleteEmployeeById("E001");
    }

    @Test
    void testGetAllEmployee() {
        when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(emp1, emp2));

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployee();

        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    void testAddListOfEmployee() {
        List<Employee> employees = Arrays.asList(emp1, emp2);
        when(employeeService.addListOfEmployee(employees)).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.addListOfEmployee(employees);

        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).addListOfEmployee(employees);
    }
}
