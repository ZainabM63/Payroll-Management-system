import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import apiService from '../services/apiService';
import { Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Tooltip, CircularProgress, Dialog, DialogActions, DialogContent, DialogTitle, TablePagination, TextField, Snackbar } from '@mui/material';
import { Edit as EditIcon, Delete as DeleteIcon, Info as InfoIcon, AccessTime as LeaveIcon } from '@mui/icons-material';
import './EmployeeList.css';

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
  const [employeeToDelete, setEmployeeToDelete] = useState(null);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [searchQuery, setSearchQuery] = useState('');
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    apiService
      .getEmployees()
      .then((response) => {
        setEmployees(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching employee data:', error);
        setLoading(false);
      });
  }, []);

  const handleDeleteClick = (employeeId) => {
    setEmployeeToDelete(employeeId);
    setOpenDeleteDialog(true);
  };

  const handleDeleteConfirm = () => {
    apiService
      .deleteEmployee(employeeToDelete)
      .then(() => {
        setEmployees((prevEmployees) =>
          prevEmployees.filter((emp) => emp.employeeId !== employeeToDelete)
        );
        setOpenDeleteDialog(false);
        setSnackbarMessage('Employee deleted successfully');
        setOpenSnackbar(true);
      })
      .catch((error) => {
        console.error('Error deleting employee:', error);
        setOpenDeleteDialog(false);
        setSnackbarMessage('Failed to delete employee');
        setOpenSnackbar(true);
      });
  };

  const handleLogout = () => {
    if (window.confirm('Are you sure you want to log out?')) {
      navigate('/'); // Navigate to the login page
    }
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString();
  };

  const filteredEmployees = employees.filter(
    (employee) =>
      employee.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      employee.jobTitle.toLowerCase().includes(searchQuery.toLowerCase()) ||
      employee.department.toLowerCase().includes(searchQuery.toLowerCase()) ||
      employee.employeeId.toString().includes(searchQuery) // Include employee ID search
  );

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>Payroll Management System</h1>
        <Button variant="contained" sx={{ backgroundColor: '#f44336' }} onClick={handleLogout}>
         Logout
        </Button>
      </header>
  
      <main className="dashboard-main">
        <div className="employee-list-header">
          <h2>Employee List</h2>
          <Link to="/employees/new">
            <Button variant="contained" color="primary">
              + Add Employee
            </Button>
          </Link>
  
          {/* Search Employees */}
          <TextField
            label="Search Employees"
            variant="outlined"
            size="small"
            value={searchQuery}
            onChange={handleSearchChange}
            style={{ marginLeft: '20px', width: '300px' }}
          />
  
          {/* View Deleted Employees Button */}
          <Link to="/deleted-employees">
            <Button
              variant="contained"
              color="info"
              style={{ marginLeft: '20px' }} // Adjust space between buttons
              startIcon={<DeleteIcon />}
            >
              View Deleted Employees
            </Button>
          </Link>
        </div>  
        {loading ? (
          <CircularProgress />
        ) : (
          <>
            <TableContainer component={Paper}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Employee ID</TableCell>
                    <TableCell>Name</TableCell>
                    <TableCell>Job Title</TableCell>
                    <TableCell>Salary</TableCell>
                    <TableCell>Department</TableCell>
                    <TableCell>Date of Birth</TableCell>
                    <TableCell>Date of Joining</TableCell>
                    <TableCell>Actions</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {filteredEmployees
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((employee) => (
                      <TableRow key={employee.employeeId}>
                        <TableCell>{employee.employeeId}</TableCell>
                        <TableCell>{employee.name}</TableCell>
                        <TableCell>{employee.jobTitle}</TableCell>
                        <TableCell>{employee.salary}</TableCell>
                        <TableCell>{employee.department}</TableCell>
                        <TableCell>{formatDate(employee.dateOfBirth)}</TableCell>
                        <TableCell>{formatDate(employee.dateOfJoining)}</TableCell>
                        <TableCell>
                          <div className="action-buttons">
                            <Tooltip title="Edit Employee">
                              <Button
                                variant="outlined"
                                color="primary"
                                startIcon={<EditIcon />}
                                component={Link}
                                to={`/employees/${employee.employeeId}/edit`}
                              >
                                Edit
                              </Button>
                            </Tooltip>
                            <Tooltip title="Delete Employee">
                              <Button
                                variant="outlined"
                                color="secondary"
                                startIcon={<DeleteIcon />}
                                onClick={() => handleDeleteClick(employee.employeeId)}
                              >
                                Delete
                              </Button>
                            </Tooltip>
                            <Tooltip title="Employee Details">
                              <Button
                                variant="outlined"
                                color="info"
                                startIcon={<InfoIcon />}
                                component={Link}
                                to={`/employees/${employee.employeeId}/details`}
                              >
                                Details
                              </Button>
                            </Tooltip>
                            <Tooltip title="Employee Leaves">
                              <Button
                                variant="outlined"
                                color="default"
                                startIcon={<LeaveIcon />}
                                component={Link}
                                to={`/employees/${employee.employeeId}/leaves`}
                              >
                                Leaves
                              </Button>
                            </Tooltip>
                          </div>
                        </TableCell>
                      </TableRow>
                    ))}
                </TableBody>
              </Table>
            </TableContainer>
            <TablePagination
              rowsPerPageOptions={[5, 10, 25]}
              component="div"
              count={filteredEmployees.length}
              rowsPerPage={rowsPerPage}
              page={page}
              onPageChange={handleChangePage}
              onRowsPerPageChange={handleChangeRowsPerPage}
            />
          </>
        )}
      </main>
  
      {/* Delete Confirmation Dialog */}
      <Dialog open={openDeleteDialog} onClose={() => setOpenDeleteDialog(false)}>
        <DialogTitle>Confirm Deletion</DialogTitle>
        <DialogContent>
          <p>Are you sure you want to delete this employee?</p>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDeleteDialog(false)} color="primary">
            Cancel
          </Button>
          <Button onClick={handleDeleteConfirm} color="secondary">
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
  
      {/* Snackbar for notifications */}
      <Snackbar
        open={openSnackbar}
        autoHideDuration={3000}
        onClose={() => setOpenSnackbar(false)}
        message={snackbarMessage}
      />
    </div>
  );
};

export default EmployeeList;
