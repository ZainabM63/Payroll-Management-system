package com.example.payroll_system.repository;

import com.example.payroll_system.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    @Query("SELECT p FROM Payroll p JOIN p.employee e WHERE e.employeeId = :employeeId")
    List<Payroll> findByEmployeeId(Long employeeId);
}
