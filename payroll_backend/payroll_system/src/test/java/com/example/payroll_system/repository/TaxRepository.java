package com.example.payroll_system.repository;


import com.example.payroll_system.model.Tax;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query("SELECT t FROM Tax t JOIN t.employee e WHERE e.employeeId = :employeeId")
    List<Tax> findByEmployeeId(Long employeeId);
}
