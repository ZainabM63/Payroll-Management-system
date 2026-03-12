package com.example.payroll_system.controller;



import com.example.payroll_system.dto.BankAccountDTO;
import com.example.payroll_system.service.BankAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable Long id) {
        return bankAccountService.getBankAccountById(id).orElse(null);
    }
    
    @PostMapping
    public BankAccountDTO addBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountService.addBankAccount(bankAccountDTO);
    }

    @PutMapping("/{id}")
    public BankAccountDTO updateBankAccount(@PathVariable Long id, @RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountService.updateBankAccount(id, bankAccountDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBankAccount(@PathVariable Long id) {
        return bankAccountService.deleteBankAccount(id);
    }
}
