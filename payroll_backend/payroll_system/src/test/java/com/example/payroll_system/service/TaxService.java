package com.example.payroll_system.service;
import com.example.payroll_system.dto.TaxDTO;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.model.Tax;
import com.example.payroll_system.repository.Emperepo;
import com.example.payroll_system.repository.TaxRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private Emperepo employeeRepository;  // Assuming you have this repository

    // Get all taxes (returns a list of TaxDTO)
    public List<TaxDTO> getAllTaxes() {
        return taxRepository.findAll()
                .stream()
                .map(this::mapToTaxDTO)
                .collect(Collectors.toList());
    }

    // Get tax by ID (returns TaxDTO)
    public Optional<TaxDTO> getTaxById(Long taxId) {
        return taxRepository.findById(taxId)
                .map(this::mapToTaxDTO); // Returns Optional<TaxDTO>
    }

    // Get taxes by employee ID (returns a list of TaxDTO)
    public List<TaxDTO> getTaxesByEmployeeId(Long employeeId) {
        List<Tax> taxes = taxRepository.findByEmployeeId(employeeId);
        return taxes.stream()
                .map(this::mapToTaxDTO)
                .collect(Collectors.toList());
    }

    // Add a new tax (accepts TaxDTO, converts to Tax entity)
    @Transactional
    public TaxDTO addTax(TaxDTO taxDTO) {
        if (taxDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        Employee employee = employeeRepository.findById(taxDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Tax tax = new Tax();
        tax.setTaxYear(taxDTO.getTaxYear());
        tax.setTaxableIncome(taxDTO.getTaxableIncome());
        tax.setTaxRate(taxDTO.getTaxRate());
      tax.setTaxAmount(taxDTO.getTaxableIncome()
        .multiply(taxDTO.getTaxRate())
  .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
        tax.setEmployee(employee);

        Tax savedTax = taxRepository.save(tax);
        return mapToTaxDTO(savedTax);
    }

    // Update an existing tax (accepts TaxDTO, converts to Tax entity)
    @Transactional
    public TaxDTO updateTax(Long taxId, TaxDTO taxDTO) {
        if (taxDTO.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        Employee employee = employeeRepository.findById(taxDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return taxRepository.findById(taxDTO.getTaxId())
                .map(existingTax -> {
                    existingTax.setTaxYear(taxDTO.getTaxYear());
                    existingTax.setTaxableIncome(taxDTO.getTaxableIncome());
                    existingTax.setTaxRate(taxDTO.getTaxRate());
                    existingTax.setTaxAmount(taxDTO.getTaxableIncome()
                    .multiply(taxDTO.getTaxRate())
              .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
                    
                    existingTax.setEmployee(employee);

                    Tax updatedTax = taxRepository.save(existingTax);
                    return mapToTaxDTO(updatedTax);
                })
                .orElseThrow(() -> new RuntimeException("Tax not found"));
    }

    // Delete tax by ID
    @Transactional
    public boolean deleteTax(Long taxId) {
        if (taxRepository.existsById(taxId)) {
            taxRepository.deleteById(taxId);
            return true;
        }
        return false; // Handle tax not found scenario
    }

    // Convert Tax entity to TaxDTO
    private TaxDTO mapToTaxDTO(Tax tax) {
        return new TaxDTO(
                tax.getTaxId(),
                tax.getEmployee().getEmployeeId(),
                tax.getTaxYear(),
                tax.getTaxableIncome(),
                tax.getTaxRate(),
                tax.getTaxAmount()
        );
    }
}
