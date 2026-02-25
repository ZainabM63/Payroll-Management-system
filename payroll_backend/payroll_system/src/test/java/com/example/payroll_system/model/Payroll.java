package com.example.payroll_system.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;  // The employee relationship should be here, not employeeId

    @Column(name = "salary_date")
    @Temporal(TemporalType.DATE)
    private Date salaryDate;

    @Column(name = "basic_salary", precision = 10, scale = 2)
    private BigDecimal basicSalary;

    @Column(name = "allowances", precision = 10, scale = 2)
    private BigDecimal allowances;

    @Column(name = "deductions", precision = 10, scale = 2)
    private BigDecimal deductions;

    @Column(name = "net_salary", precision = 10, scale = 2)
    private BigDecimal netSalary;

    // Getters and Setters
    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
