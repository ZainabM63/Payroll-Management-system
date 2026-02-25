package com.example.payroll_system.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payroll_system.dto.LeaveDTO;
import com.example.payroll_system.model.Employee;
import com.example.payroll_system.model.Leave;
import com.example.payroll_system.repository.LeaveRepository;

import jakarta.transaction.Transactional;
@Service
public class LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;
    
    
    public LeaveDTO mapToLeaveDTO(Leave leave) {
        return new LeaveDTO(
                leave.getLeaveId(),
                leave.getLeaveType(),
                leave.getLeaveStartDate(),
                leave.getLeaveEndDate(),
                leave.getLeaveStatus(),
                leave.getEmployee().getEmployeeId()
        );
    }

    public List<LeaveDTO> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(this::mapToLeaveDTO)
                .collect(Collectors.toList());
    }

    public List<LeaveDTO> getLeavesByEmployeeId(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId).stream()
                .map(this::mapToLeaveDTO)
                .collect(Collectors.toList());
    }

    public Optional<LeaveDTO> getLeaveById(Long leaveId) {
        return leaveRepository.findById(leaveId)
                .map(this::mapToLeaveDTO);
    }
    @Transactional
    public Leave addLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
    @Transactional
    public LeaveDTO updateLeave(Long id, LeaveDTO leaveDTO) {
        // Fetch the existing Leave entity by ID
        Optional<Leave> existingLeave = leaveRepository.findById(id);
    
        if (existingLeave.isPresent()) {
            // Get the existing Leave object
            Leave leave = existingLeave.get();
    
            // Update the fields with the new values from the DTO
            leave.setLeaveType(leaveDTO.getLeaveType());
            leave.setLeaveStartDate(leaveDTO.getLeaveStartDate());
            leave.setLeaveEndDate(leaveDTO.getLeaveEndDate());
            leave.setLeaveStatus(leaveDTO.getLeaveStatus());
    
            // Fetch the Employee and set it in the Leave entity
            Employee employee = new Employee();
            employee.setEmployeeId(leaveDTO.getEmployeeId()); // Assuming the employee exists already
            leave.setEmployee(employee);
    
            // Save the updated Leave entity
            Leave updatedLeave = leaveRepository.save(leave);
    
            // Convert the updated Leave entity to LeaveDTO and return
            return mapToLeaveDTO(updatedLeave);
        } else {
            throw new ResourceNotFoundException("Leave not found with ID: " + id);
        }
    }
    
    
    

    @Transactional
    public void deleteLeave(Long leaveId) {
        if (!leaveRepository.existsById(leaveId)) {
            throw new ResourceNotFoundException("Leave not found with id " + leaveId);
        }
        leaveRepository.deleteById(leaveId);
    }
}
