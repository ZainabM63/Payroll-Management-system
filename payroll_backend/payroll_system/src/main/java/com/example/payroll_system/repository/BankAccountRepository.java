package com.example.payroll_system.repository;

import com.example.payroll_system.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByEmployeeEmployeeId(Long employeeId);
}