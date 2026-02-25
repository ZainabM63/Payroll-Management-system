package com.example.payroll_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payroll_system.model.DeletedLeave;
@Repository
public interface DeletedLeaveRepository extends JpaRepository<DeletedLeave, Long> {
    List<DeletedLeave> findByEmployeeId(@Param("employeeId")Long id);
}