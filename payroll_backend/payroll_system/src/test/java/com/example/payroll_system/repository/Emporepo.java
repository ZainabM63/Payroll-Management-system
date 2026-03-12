package com.example.payroll_system.repository;

import com.example.payroll_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Emporepo extends JpaRepository<Employee, Long> {
    List<Employee> findByIsDeleted(boolean isDeleted);
}