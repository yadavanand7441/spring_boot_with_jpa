package com.spring.with.jpa.boot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        Employee employee = new Employee();
        employee.setId("E001");
        employee.setName("Anand");
        employee.setSalary(50000.0);
        employee.setAddress("Kolkata");

        assertEquals("E001", employee.getId());
        assertEquals("Anand", employee.getName());
        assertEquals(50000.0, employee.getSalary());
        assertEquals("Kolkata", employee.getAddress());
    }

    @Test
    void testAllArgsConstructor() {
        Employee employee = new Employee("E002", "Rahul", 60000.0, "Delhi");

        assertEquals("E002", employee.getId());
        assertEquals("Rahul", employee.getName());
        assertEquals(60000.0, employee.getSalary());
        assertEquals("Delhi", employee.getAddress());
    }

    @Test
    void testToString() {
        Employee employee = new Employee("E003", "Priya", 70000.0, "Mumbai");

        String toStringResult = employee.toString();

        assertTrue(toStringResult.contains("E003"));
        assertTrue(toStringResult.contains("Priya"));
        assertTrue(toStringResult.contains("70000.0"));
        assertTrue(toStringResult.contains("Mumbai"));
    }

    @Test
    void testEqualityOfFields() {
        Employee emp1 = new Employee("E004", "Suman", 45000.0, "Pune");
        Employee emp2 = new Employee("E004", "Suman", 45000.0, "Pune");

        // Since equals() is not overridden, they are different objects
        assertNotEquals(emp1, emp2);

        // But their field values are same
        assertEquals(emp1.getId(), emp2.getId());
        assertEquals(emp1.getName(), emp2.getName());
        assertEquals(emp1.getSalary(), emp2.getSalary());
        assertEquals(emp1.getAddress(), emp2.getAddress());
    }
}
