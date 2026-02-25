package com.example.payroll_system.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "Tax")
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id")
    private Long taxId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @Column(name = "tax_year")
    private Integer taxYear;

    @Column(name = "taxable_income", precision = 10, scale = 2)
    private BigDecimal taxableIncome;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount;

    // Getters and Setters
    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
