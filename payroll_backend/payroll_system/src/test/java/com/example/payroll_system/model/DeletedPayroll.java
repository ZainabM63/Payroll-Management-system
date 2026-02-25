package com.example.payroll_system.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "deleted_payroll")
public class DeletedPayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "salary_date")
    private LocalDate salaryDate;

    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @Column(name = "allowances")
    private BigDecimal allowances;

    @Column(name = "deductions")
    private BigDecimal deductions;

    @Column(name = "net_salary")
    private BigDecimal netSalary;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(LocalDate salaryDate) {
        this.salaryDate = salaryDate;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getAllowances() {
        return allowances;
    }

    public void setAllowances(BigDecimal allowances) {
        this.allowances = allowances;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // Getters and setters
}
