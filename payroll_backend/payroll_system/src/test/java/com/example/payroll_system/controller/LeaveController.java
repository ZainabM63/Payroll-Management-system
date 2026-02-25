package com.example.payroll_system.controller;

import com.example.payroll_system.dto.LeaveDTO;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.model.Leave;
import com.example.payroll_system.repository.Emperepo;
import com.example.payroll_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    
    
    @Autowired Emperepo employeeRepository;
    @GetMapping
    public List<LeaveDTO> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveDTO> getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveDTO> getLeavesByEmployeeId(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployeeId(employeeId);
    }

    @PostMapping
    public ResponseEntity<LeaveDTO> addLeave(@Valid @RequestBody LeaveDTO leaveDTO) {
        Leave leave = new Leave();
        leave.setLeaveType(leaveDTO.getLeaveType());
        leave.setLeaveStartDate(leaveDTO.getLeaveStartDate());
        leave.setLeaveEndDate(leaveDTO.getLeaveEndDate());
        leave.setLeaveStatus(leaveDTO.getLeaveStatus());

        Employee employee = new Employee();
        employee.setEmployeeId(leaveDTO.getEmployeeId());
        leave.setEmployee(employee);

        Leave savedLeave = leaveService.addLeave(leave);
        LeaveDTO response = leaveService.mapToLeaveDTO(savedLeave); // Use mapToLeaveDTO from LeaveService
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveDTO> updateLeave(@Valid @PathVariable Long id, @Valid @RequestBody LeaveDTO leaveDTO) {
    // Log the received DTO
    System.out.println("Received LeaveDTO: " + leaveDTO);

    // Update the leave and get the updated LeaveDTO
    LeaveDTO updatedLeaveDTO = leaveService.updateLeave(id, leaveDTO);

    // Return the updated LeaveDTO
    return ResponseEntity.ok(updatedLeaveDTO);
}

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
