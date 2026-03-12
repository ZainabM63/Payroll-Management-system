package com.example.payroll_system.service;

import com.example.payroll_system.dto.BankAccountDTO;
import com.example.payroll_system.dto.DeletedEmployeeDetailsDTO;
import com.example.payroll_system.dto.EmployeeDTO;
import com.example.payroll_system.dto.EmployeeDetailsDTO;
import com.example.payroll_system.dto.PayrollDTO;
import com.example.payroll_system.dto.TaxDTO;
import com.example.payroll_system.dto.LeaveDTO;
import com.example.payroll_system.model.DeletedBankAccount;
import com.example.payroll_system.model.DeletedEmployee;
import com.example.payroll_system.model.DeletedLeave;
import com.example.payroll_system.model.DeletedPayroll;
import com.example.payroll_system.model.DeletedTax;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.repository.DeletedBankAccountRepository;
import com.example.payroll_system.repository.DeletedEmployeeRepository;
import com.example.payroll_system.repository.DeletedLeaveRepository;
import com.example.payroll_system.repository.DeletedPayrollRepository;
import com.example.payroll_system.repository.DeletedTaxRepository;
import com.example.payroll_system.repository.Emperepo;


import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    @Autowired
    private Emperepo employeeRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private TaxService taxService;

     @Autowired
private DeletedEmployeeRepository deletedEmployeeRepository;

@Autowired
private DeletedPayrollRepository deletedPayrollRepository;

@Autowired
private DeletedTaxRepository deletedTaxRepository;

@Autowired
private DeletedBankAccountRepository deletedBankAccountRepository;

@Autowired
private DeletedLeaveRepository deletedLeaveRepository;
    // Method to get bank accounts by employee ID
    

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::mapToEmployeeDTO);
    }

    public EmployeeDetailsDTO getEmployeeDetails(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            List<BankAccountDTO> bankAccounts = bankAccountService.getBankAccountsByEmployeeId(id);
            List<LeaveDTO> leaves = leaveService.getLeavesByEmployeeId(id);
            List<PayrollDTO> payrolls = payrollService.getPayrollsByEmployeeId(id);
            List<TaxDTO> taxes = taxService.getTaxesByEmployeeId(id);

            return new EmployeeDetailsDTO(employee.getEmployeeId(), employee.getName(), employee.getJobTitle(),
                    employee.getDepartment(), employee.getDateOfBirth(), employee.getDateOfJoining(),
                    employee.getSalary(), payrolls, leaves, taxes, bankAccounts);
        }
        return null; // Handle employee not found scenario
    }
  
    @Transactional
    public void addEmployeeWithDetails(EmployeeDetailsDTO employeeForm) {
    Logger log = LoggerFactory.getLogger(EmployeeService.class);
    try {
        log.debug("Starting to add employee: {}", employeeForm);

        // Validate input
        if (employeeForm.getName() == null || employeeForm.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name is required");
        }

        // Save employee
        Employee employee = new Employee();
        employee.setName(employeeForm.getName());
        employee.setJobTitle(employeeForm.getJobTitle());
        employee.setDepartment(employeeForm.getDepartment());
        employee.setDateOfBirth(employeeForm.getDateOfBirth());
        employee.setDateOfJoining(employeeForm.getDateOfJoining());
        employee.setSalary(employeeForm.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);

        log.debug("Employee and related details saved successfully"+savedEmployee);
    } catch (Exception e) {
        log.error("Error adding employee with details: {}", e.getMessage(), e);
        throw e;
    }
}
    
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            // Only update fields that are provided in the DTO
            if (employeeDTO.getName() != null) employee.setName(employeeDTO.getName());
            if (employeeDTO.getJobTitle() != null) employee.setJobTitle(employeeDTO.getJobTitle());
            if (employeeDTO.getDepartment() != null) employee.setDepartment(employeeDTO.getDepartment());
            if (employeeDTO.getDateOfBirth() != null) employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            if (employeeDTO.getDateOfJoining() != null) employee.setDateOfJoining(employeeDTO.getDateOfJoining());
            if (employeeDTO.getSalary() != null) employee.setSalary(employeeDTO.getSalary());

            // Save updated employee and return DTO
            Employee updatedEmployee = employeeRepository.save(employee);
            return mapToEmployeeDTO(updatedEmployee);
        }
        return null; // Return null if employee not found
    }
@Transactional
public boolean deleteEmployee(Long id) {
    Optional<Employee> employeeOpt = employeeRepository.findById(id);
    
    if (employeeOpt.isPresent()) {
        Employee employee = employeeOpt.get();

        // 1. Save DeletedEmployee FIRST to get the new generated ID
        DeletedEmployee de = new DeletedEmployee();
        de.setName(employee.getName());
        de.setJobTitle(employee.getJobTitle());
        de.setDepartment(employee.getDepartment());
        de.setSalary(employee.getSalary());
        de.setDeletedAt(java.time.LocalDateTime.now());
        
        if (employee.getDateOfBirth() != null) {
            de.setDateOfBirth(new java.sql.Date(employee.getDateOfBirth().getTime()).toLocalDate());
        }
        if (employee.getDateOfJoining() != null) {
            de.setDateOfJoining(new java.sql.Date(employee.getDateOfJoining().getTime()).toLocalDate());
        }

        // SAVE and get the new ID
        DeletedEmployee savedDe = deletedEmployeeRepository.save(de);
        Long newDeletedId = savedDe.getEmployeeId(); 

     // 2. Copy to DeletedLeaves
List<LeaveDTO> leaves = leaveService.getLeavesByEmployeeId(id);
if (leaves != null) {
    for (LeaveDTO l : leaves) {
        DeletedLeave dl = new DeletedLeave();
        
        // Use the NEW ID generated for the deleted employee
        dl.setEmployeeId(newDeletedId); 
        
        // Copy data using exact getters from your LeaveDTO
        dl.setLeaveType(l.getLeaveType());
        dl.setLeaveStatus(l.getLeaveStatus());

        // Safe Date Conversions
        if (l.getLeaveStartDate() != null) {
            dl.setLeaveStartDate(new java.sql.Date(l.getLeaveStartDate().getTime()).toLocalDate());
        }
        if (l.getLeaveEndDate() != null) {
            dl.setLeaveEndDate(new java.sql.Date(l.getLeaveEndDate().getTime()).toLocalDate());
        }

        deletedLeaveRepository.save(dl);
    }
}
        List<BankAccountDTO> bankAccounts = bankAccountService.getBankAccountsByEmployeeId(id);
        if (bankAccounts != null) {
            bankAccounts.forEach(b -> {
                DeletedBankAccount db = new DeletedBankAccount();
                db.setEmployeeId(newDeletedId); // Use new ID
                db.setBankName(b.getBankName());
                db.setAccountNumber(b.getAccountNumber());
                deletedBankAccountRepository.save(db);
            });
        }

        // Apply the same logic for Payroll and Tax
       // 4. Copy to DeletedPayrolls
List<PayrollDTO> payrolls = payrollService.getPayrollsByEmployeeId(id);
if (payrolls != null) {
    for (PayrollDTO p : payrolls) {
        DeletedPayroll dp = new DeletedPayroll();
        dp.setEmployeeId(newDeletedId); 
        dp.setBasicSalary(p.getBasicSalary());
        dp.setAllowances(p.getAllowances());
        dp.setDeductions(p.getDeductions());
        dp.setNetSalary(p.getNetSalary());
        
        // Use salaryDate from your PayrollDTO
        if (p.getSalaryDate() != null) {
            dp.setSalaryDate(new java.sql.Date(p.getSalaryDate().getTime()).toLocalDate());
        }
        
        deletedPayrollRepository.save(dp);
    }
}

// 5. Copy to DeletedTaxes
List<TaxDTO> taxes = taxService.getTaxesByEmployeeId(id);
if (taxes != null) {
    for (TaxDTO t : taxes) {
        DeletedTax dt = new DeletedTax();
        dt.setEmployeeId(newDeletedId);
        dt.setTaxYear(t.getTaxYear());
        dt.setTaxAmount(t.getTaxAmount());
        dt.setTaxableIncome(t.getTaxableIncome());
        dt.setTaxRate(t.getTaxRate());
        
        deletedTaxRepository.save(dt);
    }
}

        // 3. Delete original
        employeeRepository.delete(employee);
        return true;
    }
    return false;
}
    private EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getJobTitle(),
                employee.getDepartment(), employee.getDateOfBirth(), employee.getDateOfJoining(), employee.getSalary());
    }

public DeletedEmployeeDetailsDTO getFullDeletedDetails(Long deletedId) {
    DeletedEmployeeDetailsDTO details = new DeletedEmployeeDetailsDTO();
    
    // 1. Get the main deleted employee info
    DeletedEmployee de = deletedEmployeeRepository.findById(deletedId)
        .orElseThrow(() -> new RuntimeException("Deleted record not found"));
    
    details.setEmployee(de);

    // 2. Get all sub-details using the methods we just created
    details.setLeaves(deletedLeaveRepository.findByEmployeeId(deletedId));
    details.setPayrolls(deletedPayrollRepository.findByEmployeeId(deletedId));
    details.setTaxes(deletedTaxRepository.findByEmployeeId(deletedId));
    details.setBankAccounts(deletedBankAccountRepository.findByEmployeeId(deletedId));

    return details;
}
}