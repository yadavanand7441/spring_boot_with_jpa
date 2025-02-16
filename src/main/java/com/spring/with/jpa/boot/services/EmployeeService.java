package com.spring.with.jpa.boot.services;

import com.spring.with.jpa.boot.entity.Employee;
import com.spring.with.jpa.boot.exceptions.ResourceNotFoundException;
import com.spring.with.jpa.boot.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // adding single employee data with all defined field
    public Employee createEmployee(Employee employee)
    {
        String randomEmployeeId= UUID.randomUUID().toString();
        employee.setId(randomEmployeeId);
        return employeeRepository.save(employee);
    }

    //fetching single employee with given id
    public Employee getEmployeeById(String employeeId)
    {
        Employee employeeById = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("employee is not presented in database with given id: " + employeeId));
        return employeeById;
    }

    //updating employee data with the help of employee id
    public Employee updateEmployeeById(Employee employee,String employeeId)
    {
        Employee updatedEmployee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("employee data is not found with given id: " + employeeId));

        //updating employee data
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setAddress(employee.getAddress());

        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }

    //deleting employee by id
    public Employee deleteEmployeeById(String employeeId)
    {
        Employee deleteEmployeeById = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("employee data is not found for deletion with given id" + employeeId));
        employeeRepository.delete(deleteEmployeeById);
        return deleteEmployeeById;
    }

    //fetching list of employees
    public List<Employee>getAllEmployee()
    {
        return employeeRepository.findAll();
    }

    //adding list of employees
    public List<Employee>addListOfEmployee(List<Employee>employees)
    {
        for (Employee employee:employees)
        {
            employee.setId(UUID.randomUUID().toString());
        }
        return employeeRepository.saveAll(employees);
    }
}
