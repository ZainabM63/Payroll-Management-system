package com.example.payroll_system.dto;

import java.util.Date;

public class LeaveDTO {

    private Long leaveId;
    private String leaveType;
    private Date leaveStartDate;
    private Date leaveEndDate;
    private String leaveStatus;
    private Long employeeId;

    // Constructors
    public LeaveDTO(Long leaveId, String leaveType, Date leaveStartDate, 
                    Date leaveEndDate, String leaveStatus, Long employeeId) {
        this.leaveId = leaveId;
        this.leaveType = leaveType;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.leaveStatus = leaveStatus;
        this.employeeId = employeeId;
    }

    // Getters and Setters
    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
