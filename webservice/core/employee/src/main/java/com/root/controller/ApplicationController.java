package com.root.controller;

import com.root.dto.EmployeeDto;
import com.root.entity.Employee;
import com.root.entity.Salary;
import com.root.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequiredArgsConstructor
@Validated
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.saveEmployee(employeeDto));

    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployeeSalary(
            @PathVariable(value = "id") final Long employeeId,
            @RequestParam("sum") final Integer sum) {

        final Optional<Employee> employeeOptional = applicationService.findEmployeeById(employeeId);

        if (!employeeOptional.isPresent()) {
            ResponseEntity.badRequest()
                    .build();
        }

        return ResponseEntity.accepted()
                .body(applicationService.updateEmployeeSalary(employeeOptional.get(), sum));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable(value = "id") final Long employeeId) {

        final Optional<Employee> employeeOptional = applicationService.findEmployeeById(employeeId);

        if (!employeeOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok()
                .body(employeeOptional.get());
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(applicationService.findAllEmployee());
    }

    @GetMapping("/salaryes")
    public ResponseEntity<List<Salary>> findAllSalaryes() {
        return ResponseEntity.ok(applicationService.findAllSalaryes());
    }

    @DeleteMapping("/deleteEmployees")
    public ResponseEntity deleteAllEmployees() {
        applicationService.deleteAllEmployeeWithSalary();

        return ResponseEntity.accepted()
                .build();
    }
}
