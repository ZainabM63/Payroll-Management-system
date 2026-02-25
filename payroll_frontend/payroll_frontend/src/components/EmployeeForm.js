

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { TextField, Button, Typography, Box, Grid, Container, Snackbar } from '@mui/material';
import BankAccountForm from './BankAccountForm';
import TaxForm from './TaxForm';
import PayrollForm from './PayrollForm';

const EmployeeForm = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    // State for employee data
    const [employeeData, setEmployeeData] = useState({
        name: '',
        jobTitle: '',
        department: '',
        dateOfBirth: '',
        dateOfJoining: '',
        salary: '',
    });

    const [bankAccountData, setBankAccountData] = useState([{ bankName: '', accountNumber: '' }]);
    const [taxData, setTaxData] = useState([{ taxYear: '', taxableIncome: '', taxRate: '', taxAmount: '' }]);
    const [payrollData, setPayrollData] = useState([{ salaryDate: '', basicSalary: '', allowances: '', deductions: '' }]);
    
    const [isUpdated, setIsUpdated] = useState(false);
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [errorSnackbar, setErrorSnackbar] = useState(false);

    useEffect(() => {
        if (id) {
            axios.get(`http://localhost:8080/api/employees/${id}/details`)
                .then((response) => {
                    const employee = response.data;
                    setEmployeeData({
                        name: employee.name,
                        jobTitle: employee.jobTitle,
                        department: employee.department,
                        dateOfBirth: employee.dateOfBirth,
                        dateOfJoining: employee.dateOfJoining,
                        salary: employee.salary,
                    });
                    setBankAccountData(employee.bankAccounts || [{ bankName: '', accountNumber: '' }]);
                    setTaxData(employee.taxes || [{ taxYear: '', taxableIncome: '', taxRate: '', taxAmount: '' }]);
                    setPayrollData(employee.payrolls || [{ salaryDate: '', basicSalary: '', allowances: '', deductions: '' }]);
                })
                .catch((error) => {
                    console.error('Error fetching employee data:', error);
                });
        }
    }, [id]);

    // Handle input changes
    const handleInputChange = (e, type, index = null) => {
        const { name, value } = e.target;
        if (type === 'employee') {
            setEmployeeData((prev) => ({ ...prev, [name]: value }));
        } else if (type === 'bankAccount') {
            const updatedData = [...bankAccountData];
            updatedData[index] = { ...updatedData[index], [name]: value };
            setBankAccountData(updatedData);
        } else if (type === 'tax') {
            const updatedData = [...taxData];
            updatedData[index] = { ...updatedData[index], [name]: value };
            setTaxData(updatedData);
        } else if (type === 'payroll') {
            const updatedData = [...payrollData];
            updatedData[index] = { ...updatedData[index], [name]: value };
            setPayrollData(updatedData);
        }
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        const combinedData = {
            ...employeeData,
            bankAccounts: bankAccountData,
            taxes: taxData,
            payrolls: payrollData,
        };

        // Log the combined data to check if it's properly formatted
        console.log('Form Data being sent:', combinedData);

        if (id) {
            axios.put(`http://localhost:8080/api/employees/${id}/details`, combinedData, {
                headers: { 'Content-Type': 'application/json' },
            })
                .then((response) => {
                    setIsUpdated(true);
                    setOpenSnackbar(true);
                })
                .catch((error) => {
                    console.error('Error updating employee:', error);
                    setErrorSnackbar(true);
                });
        } else {
            axios.post('http://localhost:8080/api/employees/add', combinedData, {
                headers: { 'Content-Type': 'application/json' },
            })
                .then(() => {
                    navigate('/employees');
                })
                .catch((error) => {
                    console.error('Error creating employee:', error);
                    setErrorSnackbar(true);
                });
        }
    };

    const handleBackClick = () => {
        navigate('/employees');
    };

    return (
        <Container maxWidth="md">
            <Box sx={{ padding: 3, backgroundColor: '#fff', borderRadius: 2, boxShadow: 3 }}>
                <Typography variant="h4" gutterBottom align="center">
                    {id ? 'Edit Employee' : 'Add New Employee'}
                </Typography>
                <form onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        {/* Employee Info */}
                        <Grid item xs={12}>
                            <TextField
                                label="Name"
                                fullWidth
                                variant="outlined"
                                name="name"
                                value={employeeData.name}
                                onChange={(e) => handleInputChange(e, 'employee')}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Job Title"
                                fullWidth
                                variant="outlined"
                                name="jobTitle"
                                value={employeeData.jobTitle}
                                onChange={(e) => handleInputChange(e, 'employee')}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Department"
                                fullWidth
                                variant="outlined"
                                name="department"
                                value={employeeData.department}
                                onChange={(e) => handleInputChange(e, 'employee')}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Date of Birth"
                                fullWidth
                                type="date"
                                variant="outlined"
                                name="dateOfBirth"
                                value={employeeData.dateOfBirth}
                                onChange={(e) => handleInputChange(e, 'employee')}
                                InputLabelProps={{ shrink: true }}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Date of Joining"
                                fullWidth
                                type="date"
                                variant="outlined"
                                name="dateOfJoining"
                                value={employeeData.dateOfJoining}
                                onChange={(e) => handleInputChange(e, 'employee')}
                                InputLabelProps={{ shrink: true }}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Salary"
                                fullWidth
                                type="number"
                                variant="outlined"
                                name="salary"
                                value={employeeData.salary}
                                onChange={(e) => handleInputChange(e, 'employee')}
                            />
                        </Grid>

                        {/* Bank Account Details */}
                        <Grid item xs={12}>
                            <Typography variant="h6">Bank Account Details</Typography>
                            {bankAccountData.map((account, index) => (
                                <BankAccountForm
                                    key={index}
                                    index={index}
                                    account={account}
                                    handleChange={(e) => handleInputChange(e, 'bankAccount', index)}
                                />
                            ))}
                        </Grid>

                        {/* Tax Information */}
                        <Grid item xs={12}>
                            <Typography variant="h6">Tax Information</Typography>
                            {taxData.map((tax, index) => (
                                <TaxForm
                                    key={index}
                                    index={index}
                                    tax={tax}
                                    handleChange={(e) => handleInputChange(e, 'tax', index)}
                                />
                            ))}
                        </Grid>

                        {/* Payroll Information */}
                        <Grid item xs={12}>
                            <Typography variant="h6">Payroll Information</Typography>
                            {payrollData.map((payroll, index) => (
                                <PayrollForm
                                    key={index}
                                    index={index}
                                    payroll={payroll}
                                    handleChange={(e) => handleInputChange(e, 'payroll', index)}
                                />
                            ))}
                        </Grid>

                        {/* Submit Button */}
                        <Grid item xs={12}>
                            <Button type="submit" variant="contained" color="primary" fullWidth>
                                {id ? 'Update Employee' : 'Add Employee'}
                            </Button>
                        </Grid>
                    </Grid>
                </form>

                {/* Success Snackbar */}
                <Snackbar
                    open={openSnackbar}
                    autoHideDuration={6000}
                    onClose={() => setOpenSnackbar(false)}
                    message="Employee updated successfully!"
                />

                {/* Error Snackbar */}
                <Snackbar
                    open={errorSnackbar}
                    autoHideDuration={6000}
                    onClose={() => setErrorSnackbar(false)}
                    message="There was an error saving the employee."
                />

                <Box mt={2} display="flex" justifyContent="center">
                    <Button variant="outlined" onClick={handleBackClick}>
                        Back to Employee List
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default EmployeeForm;
