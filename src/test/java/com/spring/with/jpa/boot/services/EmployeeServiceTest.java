package com.spring.with.jpa.boot.services;

import com.spring.with.jpa.boot.entity.Employee;
import com.spring.with.jpa.boot.exceptions.ResourceNotFoundException;
import com.spring.with.jpa.boot.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee emp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emp = new Employee("E001", "Anand", 50000.0, "Kolkata");
    }

    @Test
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        Employee saved = employeeService.createEmployee(emp);

        assertNotNull(saved.getId()); // id set by UUID
        assertEquals("Anand", saved.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeByIdFound() {
        when(employeeRepository.findById("E001")).thenReturn(Optional.of(emp));

        Employee found = employeeService.getEmployeeById("E001");

        assertEquals("Anand", found.getName());
        verify(employeeRepository, times(1)).findById("E001");
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById("E002")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById("E002"));
    }

    @Test
    void testUpdateEmployeeById() {
        Employee updated = new Employee("E001", "Rahul", 60000.0, "Delhi");

        when(employeeRepository.findById("E001")).thenReturn(Optional.of(emp));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

        Employee result = employeeService.updateEmployeeById(updated, "E001");

        assertEquals("Rahul", result.getName());
        assertEquals(60000.0, result.getSalary());
        assertEquals("Delhi", result.getAddress());
        verify(employeeRepository, times(1)).save(emp);
    }

    @Test
    void testUpdateEmployeeByIdNotFound() {
        Employee updated = new Employee("E002", "Rahul", 60000.0, "Delhi");

        when(employeeRepository.findById("E002")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.updateEmployeeById(updated, "E002"));
    }

    @Test
    void testDeleteEmployeeById() {
        when(employeeRepository.findById("E001")).thenReturn(Optional.of(emp));

        Employee deleted = employeeService.deleteEmployeeById("E001");

        assertEquals("Anand", deleted.getName());
        verify(employeeRepository, times(1)).delete(emp);
    }

    @Test
    void testDeleteEmployeeByIdNotFound() {
        when(employeeRepository.findById("E002")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.deleteEmployeeById("E002"));
    }

    @Test
    void testGetAllEmployee() {
        List<Employee> employees = Arrays.asList(emp);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployee();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testAddListOfEmployee() {
        List<Employee> employees = Arrays.asList(emp);
        when(employeeRepository.saveAll(anyList())).thenReturn(employees);

        List<Employee> result = employeeService.addListOfEmployee(employees);

        assertEquals(1, result.size());
        assertNotNull(result.get(0).getId());
        verify(employeeRepository, times(1)).saveAll(employees);
    }
}
