package com.example.payroll_system.repository;

import com.example.payroll_system.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    // Was: findByEmployeeId
List<Leave> findByEmployeeEmployeeId(Long employeeId);
}