package com.example.payroll_system.repository;

import com.example.payroll_system.model.DeletedPayroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeletedPayrollRepository extends JpaRepository<DeletedPayroll, Long> {
    List<DeletedPayroll> findByEmployeeId(Long employeeId);
}