

package com.example.payroll_system.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employeess")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "department")
    private String department;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "date_of_joining")
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;


    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    } 


    // Constructors, Getters, and Setters (auto-generated)
}
