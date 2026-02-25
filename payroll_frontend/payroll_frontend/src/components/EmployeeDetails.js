import React, { useState, useEffect } from 'react';
import { useParams,useNavigate } from 'react-router-dom';
import apiService from '../services/apiService';
import { Box, Typography, Table, TableHead, TableBody, TableRow, TableCell, Button, Grid, Card, CardContent, Divider } from '@mui/material';
import { Delete as DeleteIcon } from '@mui/icons-material';

function EmployeeDetails() {
  const { id } = useParams();
  const [employeeDetails, setEmployeeDetails] = useState(null);
   const navigate = useNavigate();
  useEffect(() => {
    apiService.getEmployeeDetails(id)
      .then(response => {
        setEmployeeDetails(response.data);
      })
      .catch(error => {
        console.error('Error fetching employee details:', error);
      });
  }, [id]);

  const handleDelete = (type, entityId) => {
    if (window.confirm(`Are you sure you want to delete this ${type}?`)) {
      apiService.deleteEntity(type, entityId)
        .then(() => {
          alert(`${type} deleted successfully`);
          setEmployeeDetails(prevState => ({
            ...prevState,
            [type + 's']: prevState[type + 's'].filter(item => item.id !== entityId),
          }));
        })
        .catch(error => {
          console.error(`Error deleting ${type}:`, error);
        });
    }
  };

  if (!employeeDetails) return <div>Loading...</div>;
  const handleBackClick = () => {
    navigate('/employees');
};
  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom align="center">Employee Details</Typography>
      
      <Card sx={{ marginBottom: 3 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>{employeeDetails.name} ({employeeDetails.jobTitle})</Typography>
          <Typography variant="body1">Employee ID: {employeeDetails.employeeId}</Typography>
          <Typography variant="body1">Salary: {employeeDetails.salary}</Typography>
          <Typography variant="body1">Date of Birth: {employeeDetails.dateOfBirth}</Typography>
          <Typography variant="body1">Date of Joining: {employeeDetails.dateOfJoining}</Typography>
          <Typography variant="body1">Department: {employeeDetails.department}</Typography>
        </CardContent>
      </Card>

      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          {/* Leaves Table */}
          <Card>
            <CardContent>
              <Typography variant="h6">Leaves</Typography>
              <Table sx={{ marginTop: 2 }}>
                <TableHead>
                  <TableRow>
                    <TableCell>Leave Type</TableCell>
                    <TableCell>Start Date</TableCell>
                    <TableCell>End Date</TableCell>
                    <TableCell>Status</TableCell>
                  
                  </TableRow>
                </TableHead>
                <TableBody>
                  {employeeDetails.leaves?.length > 0 ? (
                    employeeDetails.leaves.map(leave => (
                      <TableRow key={leave.leaveId}>
                        <TableCell>{leave.leaveType}</TableCell>
                        <TableCell>{leave.leaveStartDate}</TableCell>
                        <TableCell>{leave.leaveEndDate}</TableCell>
                        <TableCell>{leave.leaveStatus}</TableCell>
                        <TableCell>
                        </TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow><TableCell colSpan="5">No leave records available.</TableCell></TableRow>
                  )}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          {/* Tax Table */}
          <Card>
            <CardContent>
              <Typography variant="h6">Tax Information</Typography>
              <Table sx={{ marginTop: 2 }}>
                <TableHead>
                  <TableRow>
                    <TableCell>Tax Year</TableCell>
                    <TableCell>Taxable Income</TableCell>
                    <TableCell>Tax Rate</TableCell>
                    <TableCell>Tax Amount</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {employeeDetails.taxes?.length > 0 ? (
                    employeeDetails.taxes.map(tax => (
                      <TableRow key={tax.taxId}>
                        <TableCell>{tax.taxYear}</TableCell>
                        <TableCell>{tax.taxableIncome}</TableCell>
                        <TableCell>{tax.taxRate}</TableCell>
                        <TableCell>{tax.taxAmount}</TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow><TableCell colSpan="4">No tax records available.</TableCell></TableRow>
                  )}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          {/* Bank Account Table */}
          <Card>
            <CardContent>
              <Typography variant="h6">Bank Accounts</Typography>
              <Table sx={{ marginTop: 2 }}>
                <TableHead>
                  <TableRow>
                    <TableCell>Bank Name</TableCell>
                    <TableCell>Account ID</TableCell>
                    <TableCell>Account Number</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {employeeDetails.bankAccounts?.length > 0 ? (
                    employeeDetails.bankAccounts.map(bankAccount => (
                      <TableRow key={bankAccount.accountId}>
                        <TableCell>{bankAccount.bankName}</TableCell>
                        <TableCell>{bankAccount.accountId}</TableCell>
                        <TableCell>{bankAccount.accountNumber}</TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow><TableCell colSpan="3">No bank accounts available.</TableCell></TableRow>
                  )}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          {/* Payroll Table */}
          <Card>
            <CardContent>
              <Typography variant="h6">Payroll Information</Typography>
              <Table sx={{ marginTop: 2 }}>
                <TableHead>
                  <TableRow>
                    <TableCell>Salary Date</TableCell>
                    <TableCell>Basic Salary</TableCell>
                    <TableCell>Allowances</TableCell>
                    <TableCell>Deductions</TableCell>
                    <TableCell>Net Salary</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {employeeDetails.payrolls?.length > 0 ? (
                    employeeDetails.payrolls.map(payroll => (
                      <TableRow key={payroll.payrollId}>
                        <TableCell>{payroll.salaryDate}</TableCell>
                        <TableCell>{payroll.basicSalary}</TableCell>
                        <TableCell>{payroll.allowances}</TableCell>
                        <TableCell>{payroll.deductions}</TableCell>
                        <TableCell>{payroll.netSalary}</TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow><TableCell colSpan="5">No payroll records available.</TableCell></TableRow>
                  )}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
      <Box mt={2} display="flex" justifyContent="center">
                    <Button variant="outlined" onClick={handleBackClick}>
                        Back to Employee List
                    </Button>
                </Box>     
    </Box>
  );
}

export default EmployeeDetails;
