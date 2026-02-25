package com.example.payroll_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payroll_system.model.DeletedEmployee;

@Repository
public interface DeletedEmployeeRepository extends JpaRepository<DeletedEmployee, Long> {
    @Query("SELECT de FROM DeletedEmployee de WHERE de.employeeId = :employeeId")
    List<DeletedEmployee> findByEmployeeId(@Param("employeeId") Long employeeId);
}


