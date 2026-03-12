package com.example.payroll_system.repository;

import com.example.payroll_system.model.DeletedTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeletedTaxRepository extends JpaRepository<DeletedTax, Long> {
    List<DeletedTax> findByEmployeeId(Long employeeId);
}