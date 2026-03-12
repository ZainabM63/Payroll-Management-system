package com.example.payroll_system.repository;

import com.example.payroll_system.model.DeletedBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeletedBankAccountRepository extends JpaRepository<DeletedBankAccount, Long> {
    List<DeletedBankAccount> findByEmployeeId(Long employeeId);
}