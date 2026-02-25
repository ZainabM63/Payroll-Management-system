package com.example.payroll_system.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;




public class EmployeeDetailsDTO {
    private Long employeeId;
    @NotBlank(message = "Employee name is required")
    private String name;
    private String jobTitle;
    private String department;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private BigDecimal salary;

    
    private List<PayrollDTO> payrolls;
    private List<LeaveDTO> leaves;
    private List<TaxDTO> taxes;
    private List<BankAccountDTO> bankAccounts;
    private Date deletedAt;
    // Constructors, Getters, and Setters (can be auto-generated)

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

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

    public List<PayrollDTO> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<PayrollDTO> payrolls) {
        this.payrolls = payrolls;
    }

    public List<LeaveDTO> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveDTO> leaves) {
        this.leaves = leaves;
    }

    public List<TaxDTO> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxDTO> taxes) {
        this.taxes = taxes;
    }

    public List<BankAccountDTO> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountDTO> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public EmployeeDetailsDTO() {}

    public EmployeeDetailsDTO(Long employeeId, String name, String jobTitle, String department, Date dateOfBirth, 
                               Date dateOfJoining, BigDecimal salary, List<PayrollDTO> payrolls, 
                               List<LeaveDTO> leaves, List<TaxDTO> taxes, List<BankAccountDTO> bankAccounts) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.payrolls = payrolls;
        this.leaves = leaves;
        this.taxes = taxes;
        this.bankAccounts = bankAccounts;
    }
    public EmployeeDetailsDTO(Long employeeId, String name, String jobTitle, String department, Date dateOfBirth, 
    Date dateOfJoining, BigDecimal salary, List<PayrollDTO> payrolls, 
    List<LeaveDTO> leaves, List<TaxDTO> taxes, List<BankAccountDTO> bankAccounts,Date deletedAt) {
this.employeeId = employeeId;
this.name = name;
this.jobTitle = jobTitle;
this.department = department;
this.dateOfBirth = dateOfBirth;
this.dateOfJoining = dateOfJoining;
this.salary = salary;
this.payrolls = payrolls;
this.leaves = leaves;
this.taxes = taxes;
this.bankAccounts = bankAccounts;
this.deletedAt=deletedAt;
}

    // Getters and Setters omitted for brevity
}
