package com.spring.with.jpa.boot.controller;

import com.spring.with.jpa.boot.entity.Employee;
import com.spring.with.jpa.boot.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/boot/jpa")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Logger logger= LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/add_employee")
    public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee)
    {
        Employee saveEmployee = employeeService.createEmployee(employee);
        logger.info("employee data save into DB:{} ",saveEmployee);
        return ResponseEntity.ok(saveEmployee);
    }

    @GetMapping("/{get_employee_by_id}")
    public ResponseEntity<Employee>getEmployeeById(@PathVariable("get_employee_by_id") String employeeId)
    {
        Employee getEmployeeById = employeeService.getEmployeeById(employeeId);
        logger.info("employee data found from DB:{} ",getEmployeeById);
        return ResponseEntity.ok(getEmployeeById);
    }

    @PutMapping("/{update_employee_by_id}")
    public ResponseEntity<Employee>updateEmployeeById(@PathVariable("update_employee_by_id") String employeeId,@RequestBody Employee employee)
    {
        Employee updatedEmployee= employeeService.updateEmployeeById(employee,employeeId);
        logger.info("employee data updated in DB:{} ",updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{delete_employee_by_id}")
    public ResponseEntity<Employee>deleteEmployeeById(@PathVariable("delete_employee_by_id") String employeeId)
    {
        Employee deletedEmployee= employeeService.deleteEmployeeById(employeeId);
        logger.info("employee data deleted from DB:{} ",deletedEmployee);
        return ResponseEntity.ok(deletedEmployee);
    }

    @GetMapping(" ")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        List<Employee> allEmployee = employeeService.getAllEmployee();
        logger.info("all employee data founded that is present in DB:{} ",allEmployee);
        return ResponseEntity.ok(allEmployee);
    }


    @PostMapping("/add_list_of_employee")
    public ResponseEntity<List<Employee>> addListOfEmployee(@RequestBody List<Employee>employeeList)
    {
        List<Employee> addedListOfEmployee = employeeService.addListOfEmployee(employeeList);
        logger.info("all employee data added in DB:{} ",addedListOfEmployee);
        return ResponseEntity.ok(addedListOfEmployee);
    }
}
