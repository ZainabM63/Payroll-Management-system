package com.example.payroll_system.service;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}

class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

