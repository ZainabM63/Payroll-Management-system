package com.example.payroll_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payroll_system.dto.DeletedEmployeeDetailsDTO;
import com.example.payroll_system.model.DeletedBankAccount;
import com.example.payroll_system.model.DeletedEmployee;
import com.example.payroll_system.model.DeletedLeave;
import com.example.payroll_system.model.DeletedPayroll;
import com.example.payroll_system.model.DeletedTax;
import com.example.payroll_system.service.DeletedEntityService;

@RestController
@RequestMapping("api/deleted")
public class DeletedEntityController {

    @Autowired
    private DeletedEntityService deletedEntityService;

    @GetMapping("/employees")
    public List<DeletedEmployee> getDeletedEmployees() {
        return deletedEntityService.getDeletedEmployees();
    }

    @GetMapping("/payrolls")
    public List<DeletedPayroll> getDeletedPayrolls() {
        return deletedEntityService.getDeletedPayrolls();
    }

    @GetMapping("/taxes")
    public List<DeletedTax> getDeletedTaxes() {
        return deletedEntityService.getDeletedTaxes();
    }

    @GetMapping("/bankaccounts")
    public List<DeletedBankAccount> getDeletedBankAccounts() {
        return deletedEntityService.getDeletedBankAccounts();
    }

    @GetMapping("/leaves")
    public List<DeletedLeave> getDeletedLeaves() {
        return deletedEntityService.getDeletedLeaves();
    }
    @GetMapping("/employee/{id}/details")
public DeletedEmployeeDetailsDTO getDeletedEmployeeDetails(@PathVariable Long id) {
    return deletedEntityService.getDeletedEmployeeDetails(id);
}

@GetMapping("/employees/details")
public List<DeletedEmployeeDetailsDTO> getDeletedEmployeesWithDetails() {
    return deletedEntityService.getDeletedEmployeesWithDetails();
}

}
