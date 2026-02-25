package com.example.payroll_system.service;

import com.example.payroll_system.dto.PayrollDTO;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.model.Payroll;
import com.example.payroll_system.repository.Emperepo;
import com.example.payroll_system.repository.PayrollRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private Emperepo employeeRepository;  // Repository to fetch employees from DB

    // Get all payroll records (returns a list of PayrollDTO)
    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll()
                .stream()
                .map(this::mapToPayrollDTO)
                .collect(Collectors.toList());
    }

    // Get payroll by ID (returns PayrollDTO)
    public Optional<PayrollDTO> getPayrollById(Long payrollId) {
        return payrollRepository.findById(payrollId)
                .map(this::mapToPayrollDTO);
    }

    // Get payrolls by employee ID
    public List<PayrollDTO> getPayrollsByEmployeeId(Long employeeId) {
        List<Payroll> payrolls = payrollRepository.findByEmployeeId(employeeId);
        return payrolls.stream()
                .map(this::mapToPayrollDTO)
                .collect(Collectors.toList());
    }

    // Add a new payroll (accepts PayrollDTO, converts to Payroll entity)
    @Transactional
    public PayrollDTO addPayroll(PayrollDTO payrollDTO) {
        if (payrollDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        Employee employee = employeeRepository.findById(payrollDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + payrollDTO.getEmployeeId()));

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setSalaryDate(payrollDTO.getSalaryDate());
        payroll.setBasicSalary(payrollDTO.getBasicSalary());
        payroll.setAllowances(payrollDTO.getAllowances());
        payroll.setDeductions(payrollDTO.getDeductions());
        payroll.setNetSalary(
            payroll.getBasicSalary()
                  .add(payroll.getAllowances())
                  .subtract(payroll.getDeductions())
        );

        Payroll savedPayroll = payrollRepository.save(payroll);
        return mapToPayrollDTO(savedPayroll);
    }

    // Update an existing payroll (accepts PayrollDTO, converts to Payroll entity)
    @Transactional
    public PayrollDTO updatePayroll(Long id, PayrollDTO payrollDTO) {
        Optional<Payroll> existingPayroll = payrollRepository.findById(payrollDTO.getPayrollId());
        if (existingPayroll.isPresent()) {
            Payroll payroll = existingPayroll.get();
            payroll.setSalaryDate(payrollDTO.getSalaryDate());
            payroll.setBasicSalary(payrollDTO.getBasicSalary());
            payroll.setAllowances(payrollDTO.getAllowances());
            payroll.setDeductions(payrollDTO.getDeductions());
            payroll.setNetSalary(
            payroll.getBasicSalary()
                  .add(payroll.getAllowances())
                  .subtract(payroll.getDeductions())
        );

            Employee employee = new Employee();
            employee.setEmployeeId(payrollDTO.getEmployeeId());  // Assuming the employee exists already
            payroll.setEmployee(employee);

            Payroll updatedPayroll = payrollRepository.save(payroll);
            return mapToPayrollDTO(updatedPayroll);  // Convert to DTO and return
        } else {
            throw new RuntimeException("Payroll not found with ID: " + id);
        }
    }

    // Delete payroll by ID
    @Transactional
    public boolean deletePayroll(Long id) {
        if (payrollRepository.existsById(id)) {
            payrollRepository.deleteById(id);
            return true;  // Return true if deletion was successful
        }
        return false;  // Return false if payroll doesn't exist
    }

    // Convert Payroll entity to PayrollDTO
    private PayrollDTO mapToPayrollDTO(Payroll payroll) {
        return new PayrollDTO(
                payroll.getPayrollId(),
                payroll.getSalaryDate(),
                payroll.getBasicSalary(),
                payroll.getAllowances(),
                payroll.getDeductions(),
                payroll.getNetSalary(),
                payroll.getEmployee().getEmployeeId() // Use employee's ID here
        );
    }
}
