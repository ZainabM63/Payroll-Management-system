import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Import useNavigate
import apiService from "../services/apiService";
import {
  Box,
  Typography,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Card,
  CardContent,
  Grid,
  Divider,
  Button,
} from "@mui/material";

function DeletedEmployeeDetails() {
  const { id } = useParams(); // Retrieve employee ID from the URL
  const [employeeDetails, setEmployeeDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    apiService
      .getDeletedEmployeeById(id)
      .then((response) => {
        console.log(response.data);
        setEmployeeDetails(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching deleted details");
        setError("Error fetching details");
        setLoading(false);
      });
  }, [id]);

  if (loading) return <Typography>Loading...</Typography>;
  if (error) return <Typography color="error">Error: {error}</Typography>;

  const {
    employee = {},
    payrolls = [],
    taxes = [],
    leaves = [],
    bankAccounts = [],
  } = employeeDetails || {};

  const renderTable = (headers, rows, getRowContent, emptyMessage) => (
    <Table sx={{ marginTop: 2 }}>
      <TableHead>
        <TableRow>
          {headers.map((header, index) => (
            <TableCell key={index}>{header}</TableCell>
          ))}
        </TableRow>
      </TableHead>
      <TableBody>
        {rows.length > 0 ? (
          rows.map(getRowContent)
        ) : (
          <TableRow>
            <TableCell colSpan={headers.length}>{emptyMessage}</TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  );

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom align="center">
        Deleted Employee Details
      </Typography>

      <Card sx={{ marginBottom: 3 }}>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            {employee.name} ({employee.jobTitle})
          </Typography>
          <Typography variant="body1">Employee ID: {employee.employeeId}</Typography>
          <Typography variant="body1">Salary: {employee.salary}</Typography>
          <Typography variant="body1">Date of Birth: {employee.dateOfBirth}</Typography>
          <Typography variant="body1">Date of Joining: {employee.dateOfJoining}</Typography>
          <Typography variant="body1">Department: {employee.department}</Typography>
        </CardContent>
      </Card>

      <Divider sx={{ marginY: 2 }} />

      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Leaves</Typography>
              {renderTable(
                ["Leave Type", "Start Date", "End Date", "Status"],
                leaves,
                (leave) => (
                  <TableRow key={leave.leaveId}>
                    <TableCell>{leave.leaveType}</TableCell>
                    <TableCell>{leave.leaveStartDate}</TableCell>
                    <TableCell>{leave.leaveEndDate}</TableCell>
                    <TableCell>{leave.leaveStatus}</TableCell>
                  </TableRow>
                ),
                "No leave records available."
              )}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Tax Information</Typography>
              {renderTable(
                ["Tax Year", "Taxable Income", "Tax Rate", "Tax Amount"],
                taxes,
                (tax) => (
                  <TableRow key={tax.taxId}>
                    <TableCell>{tax.taxYear}</TableCell>
                    <TableCell>{tax.taxableIncome}</TableCell>
                    <TableCell>{tax.taxRate}</TableCell>
                    <TableCell>{tax.taxAmount}</TableCell>
                  </TableRow>
                ),
                "No tax records available."
              )}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Bank Accounts</Typography>
              {renderTable(
                ["Bank Name", "Account ID", "Account Number"],
                bankAccounts,
                (account) => (
                  <TableRow key={account.accountId}>
                    <TableCell>{account.bankName}</TableCell>
                    <TableCell>{account.accountId}</TableCell>
                    <TableCell>{account.accountNumber}</TableCell>
                  </TableRow>
                ),
                "No bank accounts available."
              )}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Payroll Information</Typography>
              {renderTable(
                ["Salary Date", "Basic Salary", "Allowances", "Deductions", "Net Salary"],
                payrolls,
                (payroll) => (
                  <TableRow key={payroll.payrollId}>
                    <TableCell>{payroll.salaryDate}</TableCell>
                    <TableCell>{payroll.basicSalary}</TableCell>
                    <TableCell>{payroll.allowances}</TableCell>
                    <TableCell>{payroll.deductions}</TableCell>
                    <TableCell>{payroll.netSalary}</TableCell>
                  </TableRow>
                ),
                "No payroll records available."
              )}
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      <Box sx={{ marginTop: 3 }}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate("/employees")} // Navigate to the employee list
        >
          Back to Employee List
        </Button>
      </Box>
    </Box>
  );
}
export default DeletedEmployeeDetails;
