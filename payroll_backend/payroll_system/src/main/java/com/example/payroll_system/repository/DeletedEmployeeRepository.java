package com.example.payroll_system.repository;

import com.example.payroll_system.model.DeletedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeletedEmployeeRepository extends JpaRepository<DeletedEmployee, Long> {
    List<DeletedEmployee> findByEmployeeId(Long employeeId);
}