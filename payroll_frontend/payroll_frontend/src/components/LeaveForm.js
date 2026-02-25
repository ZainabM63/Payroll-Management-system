
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { TextField, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography, Grid, Snackbar,Box } from '@mui/material';
import { Alert } from '@mui/lab'; // For snackbar alert

const LeaveForm = () => {
  const { id } = useParams(); // Employee ID from URL
  const navigate = useNavigate();
  const [leaves, setLeaves] = useState([]); // List of leave records
  const [leaveData, setLeaveData] = useState({
    leaveType: '',
    leaveStartDate: '',
    leaveEndDate: '',
    leaveStatus: '',
  }); // Single leave object
  const [error, setError] = useState(null); // To handle and display errors
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');

  // Fetch leave data for the employee
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/leaves/employee/${id}`)
      .then((response) => {
        setLeaves(response.data || []); // Set all leave records for the employee
      })
      .catch((error) => {
        console.error('Error fetching leave data:', error);
        setError('Failed to fetch leave data. Please try again later.');
      });
  }, [id]); // Fetch data when employee ID changes

  // Handle change in leave fields
  const handleLeaveChange = (e) => {
    const { name, value } = e.target;
    setLeaveData((prevData) => ({ ...prevData, [name]: value })); // Update specific leave field
  };

  // Add or update leave record
  const handleSubmit = (e) => {
    e.preventDefault();
  
    const leaveRequest = leaveData.leaveId
      ? axios.put(`http://localhost:8080/api/leaves/${leaveData.leaveId}`, leaveData, {
          headers: { 'Content-Type': 'application/json' },
        })
      : axios.post(`http://localhost:8080/api/leaves`, { ...leaveData, employeeId: id }, {
          headers: { 'Content-Type': 'application/json' },
        });

    leaveRequest
      .then(() => {
        setSnackbarMessage(leaveData.leaveId ? 'Leave record updated successfully!' : 'Leave record added successfully!');
        setOpenSnackbar(true);
        setLeaveData({
          leaveType: '',
          leaveStartDate: '',
          leaveEndDate: '',
          leaveStatus: '',
        }); // Reset form
        return axios.get(`http://localhost:8080/api/leaves/employee/${id}`); // Refresh list
      })
      .then((response) => setLeaves(response.data || []))
      .catch((error) => {
        console.error('Error saving leave data:', error);
        setError('Failed to save leave data. Please try again later.');
      });
  };

  // Delete leave record
  const handleDelete = (leaveId) => {
    if (window.confirm('Are you sure you want to delete this leave record?')) {
      axios
        .delete(`http://localhost:8080/api/leaves/${leaveId}`)
        .then(() => {
          setSnackbarMessage('Leave record deleted successfully!');
          setOpenSnackbar(true);
          setLeaves((prevLeaves) => prevLeaves.filter((leave) => leave.leaveId !== leaveId)); // Update list
        })
        .catch((error) => {
          console.error('Error deleting leave record:', error);
          setError('Failed to delete leave record. Please try again later.');
        });
    }
  };

  // Populate leave data in the form for editing
  const handleEdit = (leave) => {
    setLeaveData(leave);
  };

  const handleBackClick = () => {
    navigate('/employees');
};

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        Manage Leaves for Employee {id}
      </Typography>
      {error && <Alert severity="error">{error}</Alert>}

      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Leave Type"
              name="leaveType"
              fullWidth
              value={leaveData.leaveType}
              onChange={handleLeaveChange}
              variant="outlined"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Start Date"
              name="leaveStartDate"
              type="date"
              fullWidth
              value={leaveData.leaveStartDate}
              onChange={handleLeaveChange}
              InputLabelProps={{ shrink: true }}
              variant="outlined"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="End Date"
              name="leaveEndDate"
              type="date"
              fullWidth
              value={leaveData.leaveEndDate}
              onChange={handleLeaveChange}
              InputLabelProps={{ shrink: true }}
              variant="outlined"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Leave Status"
              name="leaveStatus"
              fullWidth
              value={leaveData.leaveStatus}
              onChange={handleLeaveChange}
              variant="outlined"
            />
          </Grid>
        </Grid>
        <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
          {leaveData.leaveId ? 'Update Leave' : 'Add Leave'}
        </Button>
      </form>

      <Typography variant="h6" gutterBottom sx={{ mt: 3 }}>
        Leave Records
      </Typography>
      {leaves.length > 0 ? (
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Leave Type</TableCell>
                <TableCell>Start Date</TableCell>
                <TableCell>End Date</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {leaves.map((leave) => (
                <TableRow key={leave.leaveId}>
                  <TableCell>{leave.leaveType}</TableCell>
                  <TableCell>{leave.leaveStartDate}</TableCell>
                  <TableCell>{leave.leaveEndDate}</TableCell>
                  <TableCell>{leave.leaveStatus}</TableCell>
                  <TableCell>
                    <Button variant="outlined" color="primary" onClick={() => handleEdit(leave)}>
                      Edit
                    </Button>
                    <Button variant="outlined" color="secondary" onClick={() => handleDelete(leave.leaveId)}>
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <Typography>No leave records available.</Typography>
      )}

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert severity="success">{snackbarMessage}</Alert>
      </Snackbar>
      <Box mt={2} display="flex" justifyContent="center">
                    <Button variant="outlined" onClick={handleBackClick}>
                        Back to Employee List
                    </Button>
                </Box>     
    </div>
  );
};

export default LeaveForm;
