package com.root.service;

import com.root.dto.EmployeeDto;
import com.root.entity.Employee;
import com.root.entity.Salary;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Employee> findAllEmployee();
    List<Salary> findAllSalaryes();
    Optional<Employee> findEmployeeById(Long id);
    void deleteAllEmployeeWithSalary();
    Employee saveEmployee(EmployeeDto employeeDto);
    Employee updateEmployeeSalary(Employee employee, Integer sum);

}
