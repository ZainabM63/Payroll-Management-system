package com.example.payroll_system.dto;


public class BankAccountDTO {
    private Long accountId;
    private String accountNumber;
    private String bankName;
    private Long employeeId;

    // Constructors
    public BankAccountDTO() {
    }

    public BankAccountDTO(Long accountId, String accountNumber, String bankName, Long employeeId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.employeeId = employeeId;
    }

    // Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
