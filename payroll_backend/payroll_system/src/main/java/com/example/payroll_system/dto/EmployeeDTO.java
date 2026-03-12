package com.example.payroll_system.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeDTO {

    private Long employeeId;
    private String name;
    private String jobTitle;
    private String department;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private BigDecimal salary;

    // Default Constructor
    public EmployeeDTO() {
    }

    // Parameterized Constructor
    public EmployeeDTO(Long employeeId, String name, String jobTitle, String department,
                       Date dateOfBirth, Date dateOfJoining, BigDecimal salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
