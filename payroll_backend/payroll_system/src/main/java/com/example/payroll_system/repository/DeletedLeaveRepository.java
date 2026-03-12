package com.example.payroll_system.repository;

import com.example.payroll_system.model.DeletedLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeletedLeaveRepository extends JpaRepository<DeletedLeave, Long> {
    List<DeletedLeave> findByEmployeeId(Long employeeId);
}