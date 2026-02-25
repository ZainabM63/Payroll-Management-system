package com.example.payroll_system.repository;

import com.example.payroll_system.model.Leave;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    @Query("SELECT l FROM Leave l JOIN l.employee e WHERE e.employeeId = :employeeId")
    List<Leave> findByEmployeeId(@Param("employeeId")Long employeeId);
}
