package com.example.payroll_system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "deleted_tax")
public class DeletedTax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id")
    private Long taxId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "tax_year")
    private Integer taxYear;

    @Column(name = "taxable_income")
    private BigDecimal taxableIncome;

    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // Getters and setters
}
