package com.root.service;

import com.root.dto.EmployeeDto;
import com.root.entity.Employee;
import com.root.entity.Salary;
import com.root.repository.EmployeeCrudRepository;
import com.root.repository.SalaryCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private SalaryCrudRepository salaryCrudRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeCrudRepository.findAll();
    }

    @Override
    public List<Salary> findAllSalaryes() {
        return salaryCrudRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(final Long id) {
        return employeeCrudRepository.findById(id);
    }

    @Override
    public void deleteAllEmployeeWithSalary() {
        employeeCrudRepository.deleteAll();
        salaryCrudRepository.deleteAll();
    }

    @Override
    public Employee saveEmployee(final EmployeeDto employeeDto) {
        final Salary salary = new Salary(employeeDto.getSum());
        final Employee employee = new Employee(employeeDto.getName(), salary);

        salaryCrudRepository.save(salary);
        return employeeCrudRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeSalary(final Employee employee, final Integer sum) {
        if (Objects.isNull(employee.getSalary())) {
            final Salary salary = employee.getSalary();
            salary.setSummary(sum);
            employee.setSalary(salary);
            salaryCrudRepository.save(salary);
        } else {
            final Salary salary = new Salary(sum);
            employee.setSalary(salary);
            salaryCrudRepository.save(salary);
        }

        return employeeCrudRepository.save(employee);
    }
}
