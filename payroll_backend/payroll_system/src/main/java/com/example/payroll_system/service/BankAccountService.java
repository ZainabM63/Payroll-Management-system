package com.example.payroll_system.service;

import com.example.payroll_system.model.BankAccount;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.repository.BankAccountRepository;
import com.example.payroll_system.repository.Emperepo;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.example.payroll_system.dto.BankAccountDTO;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private Emperepo employeeRepository;  // Assuming you have an Employee repository

    // Get all bank accounts (returns a list of BankAccountDTO)
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(this::mapToBankAccountDTO)
                .collect(Collectors.toList());
    }

    // Get bank account by ID (returns BankAccountDTO)
    public Optional<BankAccountDTO> getBankAccountById(Long accountId) {
        return bankAccountRepository.findById(accountId)
                .map(this::mapToBankAccountDTO); // Returns Optional<BankAccountDTO>
    }

    // Add a new bank account (accepts BankAccountDTO, converts to BankAccount entity)
    @Transactional
    public BankAccountDTO addBankAccount(BankAccountDTO bankAccountDTO) {
        if (bankAccountDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        Employee employee = employeeRepository.findById(bankAccountDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankName(bankAccountDTO.getBankName());
        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccount.setEmployee(employee);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return mapToBankAccountDTO(savedBankAccount);
    }

    @Transactional
    public BankAccountDTO updateBankAccount(Long accountId, BankAccountDTO bankAccountDTO) {
        // Validate Employee ID
        if (bankAccountDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }
    
        // Fetch associated Employee
        Employee employee = employeeRepository.findById(bankAccountDTO.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + bankAccountDTO.getEmployeeId() + " not found"));
    
        // Fetch existing BankAccount by accountId
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountDTO.getAccountId())
                .orElseThrow(() -> new BankAccountNotFoundException("Bank Account with ID " + bankAccountDTO.getAccountId()+ " not found"));
    
        // Update bank account details
        if (bankAccountDTO.getBankName() != null) {
            bankAccount.setBankName(bankAccountDTO.getBankName());
        }
        if (bankAccountDTO.getAccountNumber() != null) {
            bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
        }
        bankAccount.setEmployee(employee);
    
        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
    
        return mapToBankAccountDTO(updatedBankAccount);
    }
    
    // Get bank accounts by employee ID
    public List<BankAccountDTO> getBankAccountsByEmployeeId(Long employeeId) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByEmployeeEmployeeId(employeeId);
        return bankAccounts.stream()
                .map(this::mapToBankAccountDTO)
                .collect(Collectors.toList());
    }

    // Delete bank account by ID
    @Transactional
    public boolean deleteBankAccount(Long accountId) {
        if (bankAccountRepository.existsById(accountId)) {
            bankAccountRepository.deleteById(accountId);
            return true;
        }
        return false; // Handle bank account not found scenario
    }

    // Convert BankAccount entity to BankAccountDTO
    private BankAccountDTO mapToBankAccountDTO(BankAccount bankAccount) {
        return new BankAccountDTO(
                bankAccount.getAccountId(),
                bankAccount.getAccountNumber(),
                bankAccount.getBankName(),
                bankAccount.getEmployee().getEmployeeId()
        );

     
            }
}
