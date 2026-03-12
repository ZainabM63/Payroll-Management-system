package com.example.payroll_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.payroll_system.model.DeletedEmployee;
import com.example.payroll_system.dto.DeletedEmployeeDetailsDTO;
import com.example.payroll_system.model.DeletedBankAccount;
import com.example.payroll_system.model.DeletedLeave;
import com.example.payroll_system.model.DeletedPayroll;
import com.example.payroll_system.model.DeletedTax;
import com.example.payroll_system.repository.DeletedBankAccountRepository;
import com.example.payroll_system.repository.DeletedEmployeeRepository;
import com.example.payroll_system.repository.DeletedLeaveRepository;
import com.example.payroll_system.repository.DeletedPayrollRepository;
import com.example.payroll_system.repository.DeletedTaxRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class DeletedEntityService {
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


    @Transactional
    public List<DeletedEmployeeDetailsDTO> getDeletedEmployeesWithDetails() {
        List<DeletedEmployee> deletedEmployees = deletedEmployeeRepository.findAll();
        List<DeletedEmployeeDetailsDTO> detailsDTOList = new ArrayList<>();
    
        for (DeletedEmployee employee : deletedEmployees) {
            List<DeletedPayroll> payrolls = deletedPayrollRepository.findByEmployeeId(employee.getEmployeeId());
            List<DeletedTax> taxes = deletedTaxRepository.findByEmployeeId(employee.getEmployeeId());
            List<DeletedLeave> leaves = deletedLeaveRepository.findByEmployeeId(employee.getEmployeeId());
            List<DeletedBankAccount> bankAccounts = deletedBankAccountRepository.findByEmployeeId(employee.getEmployeeId());
    
            DeletedEmployeeDetailsDTO detailsDTO = new DeletedEmployeeDetailsDTO();
            detailsDTO.setEmployee(employee);
            detailsDTO.setPayrolls(payrolls);
            detailsDTO.setTaxes(taxes);
            detailsDTO.setLeaves(leaves);
            detailsDTO.setBankAccounts(bankAccounts);
    
            detailsDTOList.add(detailsDTO);
        }
    
        return detailsDTOList;
    }
    
    @Transactional
    public List<DeletedEmployee> getDeletedEmployees() {
        return deletedEmployeeRepository.findAll();
    }
    @Transactional
    public List<DeletedPayroll> getDeletedPayrolls() {
        return deletedPayrollRepository.findAll();
    }
    @Transactional
    public List<DeletedTax> getDeletedTaxes() {
        return deletedTaxRepository.findAll();
    }
    @Transactional
    public List<DeletedBankAccount> getDeletedBankAccounts() {
        return deletedBankAccountRepository.findAll();
    }
    @Transactional
    public List<DeletedLeave> getDeletedLeaves() {
        return deletedLeaveRepository.findAll();
    }
   @Transactional
   public DeletedEmployeeDetailsDTO getDeletedEmployeeDetails(Long id) {
        DeletedEmployee employee = deletedEmployeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deleted employee not found with id: " + id));

        List<DeletedPayroll> payrolls = deletedPayrollRepository.findByEmployeeId(id);
        List<DeletedTax> taxes = deletedTaxRepository.findByEmployeeId(id);
        List<DeletedLeave> leaves = deletedLeaveRepository.findByEmployeeId(id);
        List<DeletedBankAccount> bankAccounts = deletedBankAccountRepository.findByEmployeeId(id);

        DeletedEmployeeDetailsDTO detailsDTO = new DeletedEmployeeDetailsDTO();
        detailsDTO.setEmployee(employee);
        detailsDTO.setPayrolls(payrolls);
        detailsDTO.setTaxes(taxes);
        detailsDTO.setLeaves(leaves);
        detailsDTO.setBankAccounts(bankAccounts);

        return detailsDTO;
    }
}

