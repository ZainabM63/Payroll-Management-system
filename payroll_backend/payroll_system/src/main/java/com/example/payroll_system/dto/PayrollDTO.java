package com.example.payroll_system.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PayrollDTO {
    private Long payrollId;
    private Long employeeId;  // Use 'employeeId' for DTO, not 'employee_id'

    private Date salaryDate;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal netSalary;

    // Getters and Setters
    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
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

    // Constructor for PayrollDTO (employeeId should be long)
    public PayrollDTO(Long payrollId, Date salaryDate, BigDecimal basicSalary,
                      BigDecimal allowances, BigDecimal deductions, BigDecimal netSalary, long employeeId) {
        this.payrollId = payrollId;
        this.salaryDate = salaryDate;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.employeeId = employeeId;  // Corrected field name
    }

    // Default constructor
    public PayrollDTO() {
    }
}
