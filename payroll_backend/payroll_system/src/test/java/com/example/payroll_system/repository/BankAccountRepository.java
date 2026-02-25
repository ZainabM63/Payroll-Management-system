package com.example.payroll_system.repository;

import com.example.payroll_system.model.BankAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    // Custom query to find BankAccounts by employeeId
    @Query("SELECT b FROM BankAccount b JOIN b.employee e WHERE e.employeeId = :employeeId")
    List<BankAccount> findByEmployeeId(Long employeeId);
    Optional<BankAccount> findById(Long accountId);
}



