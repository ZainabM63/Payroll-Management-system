package com.example.payroll_system.dto;

import java.util.List;

import com.example.payroll_system.model.DeletedBankAccount;
import com.example.payroll_system.model.DeletedEmployee;
import com.example.payroll_system.model.DeletedLeave;
import com.example.payroll_system.model.DeletedPayroll;
import com.example.payroll_system.model.DeletedTax;

public class DeletedEmployeeDetailsDTO {
    private DeletedEmployee employee;
    private List<DeletedPayroll> payrolls;
    private List<DeletedTax> taxes;
    private List<DeletedLeave> leaves;
    private List<DeletedBankAccount> bankAccounts;
	
	public DeletedEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(DeletedEmployee employee) {
		this.employee = employee;
	}
	public List<DeletedPayroll> getPayrolls() {
		return payrolls;
	}
	public void setPayrolls(List<DeletedPayroll> payrolls) {
		this.payrolls = payrolls;
	}
	public List<DeletedTax> getTaxes() {
		return taxes;
	}
	public void setTaxes(List<DeletedTax> taxes) {
		this.taxes = taxes;
	}
	public List<DeletedLeave> getLeaves() {
		return leaves;
	}
	public void setLeaves(List<DeletedLeave> leaves) {
		this.leaves = leaves;
	}
	public List<DeletedBankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<DeletedBankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

}
