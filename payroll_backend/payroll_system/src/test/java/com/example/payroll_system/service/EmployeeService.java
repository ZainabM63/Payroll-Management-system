package com.example.payroll_system.service;

import com.example.payroll_system.dto.BankAccountDTO;
import com.example.payroll_system.dto.EmployeeDTO;
import com.example.payroll_system.dto.EmployeeDetailsDTO;
import com.example.payroll_system.dto.PayrollDTO;
import com.example.payroll_system.dto.TaxDTO;
import com.example.payroll_system.dto.LeaveDTO;
import com.example.payroll_system.model.Employee;
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

        log.debug("Employee and related details saved successfully");
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
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false; // Handle employee not found scenario
    }

    private EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getJobTitle(),
                employee.getDepartment(), employee.getDateOfBirth(), employee.getDateOfJoining(), employee.getSalary());
    }
}