package com.root.repository;

import org.springframework.data.repository.CrudRepository;
import com.root.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCrudRepository extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();
}
