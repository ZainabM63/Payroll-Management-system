package com.example.payroll_system.repository;


import com.example.payroll_system.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Emperepo extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.isDeleted = :isDeleted")
    List<Employee> findByIsDeleted(@Param("isDeleted") boolean isDeleted); 
}
