package com.spring.with.jpa.boot.repositories;

import com.spring.with.jpa.boot.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeRepositoryTest {


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Test
    void testSaveAndFindById() {
        Employee emp = new Employee("E001", "Anand", 50000.0, "Kolkata");
        employeeRepository.save(emp);

        assertTrue(employeeRepository.findById("E001").isPresent());
    }
}
