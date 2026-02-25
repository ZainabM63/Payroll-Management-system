package com.example.payroll_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payroll_system.model.DeletedTax;
@Repository
public interface DeletedTaxRepository extends JpaRepository<DeletedTax, Long> {
    List<DeletedTax> findByEmployeeId(Long id);}