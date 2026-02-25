package com.example.payroll_system.dto;
import java.math.BigDecimal;
public class TaxDTO {
    
    private Long taxId;
    private Long employeeId;
    private Integer taxYear;
    private BigDecimal taxableIncome;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;
    
    
    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Integer getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(Integer taxYear) {
        this.taxYear = taxYear;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // Constructors
    public TaxDTO() {
    }
    public TaxDTO(Long taxId, Long employeeId, Integer taxYear, BigDecimal taxableIncome, BigDecimal taxRate, BigDecimal taxAmount) {
        this.taxId = taxId;
        this.employeeId = employeeId;
        this.taxYear = taxYear;
        this.taxableIncome = taxableIncome;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }
}

