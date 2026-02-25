package com.example.payroll_system.controller;


import com.example.payroll_system.dto.PayrollDTO;

import com.example.payroll_system.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // Get all payroll records
    @GetMapping
    public List<PayrollDTO> getAllPayrolls() {
        return payrollService.getAllPayrolls();  // Returns list of PayrollDTO
    }

    // Get payroll by ID
    @GetMapping("/{id}")
    public PayrollDTO getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id)
                .map(payroll -> new PayrollDTO(
                        payroll.getPayrollId(),
                        payroll.getSalaryDate(),
                        payroll.getBasicSalary(),
                        payroll.getAllowances(),
                        payroll.getDeductions(),
                        payroll.getNetSalary(),
                        payroll.getEmployeeId())) // Convert to DTO manually
                .orElse(null);  // Return null if not found
    }

    // Add new payroll
    @PostMapping("/addPayroll")
    public PayrollDTO addPayroll(@RequestBody PayrollDTO payrollDTO) {
         payrollService.addPayroll(payrollDTO); // Pass DTO directly to service
                 return payrollDTO;
    }

    // Update existing payroll
    @PutMapping("/{id}")
    public PayrollDTO updatePayroll(@PathVariable Long id, @RequestBody PayrollDTO payrollDTO) {
        return payrollService.updatePayroll(id, payrollDTO); // Pass DTO directly to service
    }

    // Delete payroll by ID
    @DeleteMapping("/{id}")
    public boolean deletePayroll(@PathVariable Long id) {
        return payrollService.deletePayroll(id);  // Returns boolean from service
    }
}
