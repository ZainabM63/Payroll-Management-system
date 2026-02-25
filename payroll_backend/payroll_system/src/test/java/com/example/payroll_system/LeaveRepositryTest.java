package com.example.payroll_system;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.payroll_system.repository.LeaveRepository;
import com.example.payroll_system.model.Leave;

@SpringBootTest
public class LeaveRepositryTest{

    @Autowired
    private LeaveRepository leaveRepository;  // Use LeaveRepository, not LeaveRepositoryTest

    @Test
    public void testFindByEmployeeId() {
        Long employeeId = 1L; // Replace with an actual ID in your database
        List<Leave> leaves = leaveRepository.findByEmployeeId(employeeId);
        System.out.println("Leaves: " + leaves);
    }
}
