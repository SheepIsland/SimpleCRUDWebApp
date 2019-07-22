package com.root.repository;

import com.root.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryCrudRepository extends CrudRepository<Salary, Long> {
    @Override
    List<Salary> findAll();
}
