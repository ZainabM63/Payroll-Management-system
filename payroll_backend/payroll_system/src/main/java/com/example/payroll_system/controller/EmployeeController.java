package com.example.payroll_system.controller;
import com.example.payroll_system.dto.BankAccountDTO;
import com.example.payroll_system.dto.EmployeeDTO;
import com.example.payroll_system.dto.EmployeeDetailsDTO;
import com.example.payroll_system.dto.PayrollDTO;
import com.example.payroll_system.dto.TaxDTO;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.repository.Emperepo;
import com.example.payroll_system.service.BankAccountService;
import com.example.payroll_system.service.EmployeeService;
import com.example.payroll_system.service.PayrollService;
import com.example.payroll_system.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Emperepo employeeRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private TaxService taxService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/{id}/details")
    public EmployeeDetailsDTO getEmployeeDetails(@PathVariable Long id) {
        return employeeService.getEmployeeDetails(id);
    }

    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployeeWithDetails(@RequestBody EmployeeDetailsDTO employeeForm) {
        try {
            // Save employee
            Employee employee = new Employee();
            employee.setName(employeeForm.getName());
            employee.setJobTitle(employeeForm.getJobTitle());
            employee.setDepartment(employeeForm.getDepartment());
            employee.setDateOfBirth(employeeForm.getDateOfBirth());
            employee.setDateOfJoining(employeeForm.getDateOfJoining());
            employee.setSalary(employeeForm.getSalary());

            employeeRepository.save(employee); // Saving the employee first

            // Get the generated employee_id
            Long employeeId = employee.getEmployeeId(); // Make sure the ID is generated

            // Now, add bank accounts, payrolls, and taxes
            if (employeeForm.getBankAccounts() != null) {
                for (BankAccountDTO bankAccount : employeeForm.getBankAccounts()) {
                    bankAccount.setEmployeeId(employeeId); // Setting the employee_id from saved employee
                    bankAccountService.addBankAccount(bankAccount);
                }
            }

            if (employeeForm.getPayrolls() != null) {
                for (PayrollDTO payroll : employeeForm.getPayrolls()) {
                    payroll.setEmployeeId(employeeId); // Setting the employee_id from saved employee
                    payrollService.addPayroll(payroll);
                }
            }

            if (employeeForm.getTaxes() != null) {
                for (TaxDTO tax : employeeForm.getTaxes()) {
                    tax.setEmployeeId(employeeId); // Setting the employee_id from saved employee
                    taxService.addTax(tax);
                }
            }

            return ResponseEntity.ok("Employee and related details added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding employee: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/details")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDetailsDTO employeeForm) {
        try {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if (!existingEmployee.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
    
            Employee employee = existingEmployee.get();
    
            // Update employee details
            employee.setName(employeeForm.getName());
            employee.setJobTitle(employeeForm.getJobTitle());
            employee.setDepartment(employeeForm.getDepartment());
            employee.setDateOfBirth(employeeForm.getDateOfBirth());
            employee.setDateOfJoining(employeeForm.getDateOfJoining());
            employee.setSalary(employeeForm.getSalary());
            
            // Save updated employee
            employeeRepository.save(employee);
    
            // Update child entities if provided
            if (employeeForm.getBankAccounts() != null) {
                for (BankAccountDTO bankAccount : employeeForm.getBankAccounts()) {
                    bankAccount.setEmployeeId(id);
                    bankAccountService.updateBankAccount(id, bankAccount);
                }
            }
    
            if (employeeForm.getPayrolls() != null) {
                for (PayrollDTO payroll : employeeForm.getPayrolls()) {
                    payroll.setEmployeeId(id);
                    payrollService.updatePayroll(id, payroll);
                }
            }
    
            if (employeeForm.getTaxes() != null) {
                for (TaxDTO tax : employeeForm.getTaxes()) {
                    tax.setEmployeeId(id);
                    taxService.updateTax(id, tax);
                }
            }
    
            return ResponseEntity.ok("Employee and related details updated successfully");
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
   
}
