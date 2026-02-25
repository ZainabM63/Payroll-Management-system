import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom"; // Import useNavigate here
import {
  Button,
  CircularProgress,
  Card,
  CardContent,
  Grid,
  Typography,
  Box,
} from "@mui/material";
import apiService from "../services/apiService";

const DeletedEmployeeList = () => {
  const [deletedEmployees, setDeletedEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate(); // Initialize navigate here

  useEffect(() => {
    const fetchDeletedEmployees = async () => {
      try {
        const response = await apiService.getDeletedEmployees();
        setDeletedEmployees(response.data);
      } catch (err) {
        setError(err.message || "Failed to fetch deleted employees");
      } finally {
        setLoading(false);
      }
    };

    fetchDeletedEmployees();
  }, []);

  if (loading)
    return (
      <Box display="flex" justifyContent="center" alignItems="center" height="100vh">
        <CircularProgress />
      </Box>
    );
  if (error)
    return (
      <Box display="flex" justifyContent="center" alignItems="center" height="100vh">
        <Typography variant="h6" color="error">
          {`Error: ${error}`}
        </Typography>
      </Box>
    );

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom>
        Deleted Employees
      </Typography>
      <Grid container spacing={3}>
        {deletedEmployees.map((employee) => (
          <Grid item xs={12} sm={6} md={4} key={employee.employeeId}>
            <Card variant="outlined">
              <CardContent>
                <Typography variant="h6" component="div">
                  {employee.name}
                </Typography>
                <Typography color="text.secondary">
                  <strong>Job Title:</strong> {employee.jobTitle}
                </Typography>
                <Typography color="text.secondary">
                  <strong>Department:</strong> {employee.department}
                </Typography>
                <Box mt={2}>
                  <Link to={`/deleted-employees/${employee.employeeId}`}>
                    <Button variant="contained" color="primary">
                      View Details
                    </Button>
                  </Link>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Button
        variant="contained"
        color="primary"
        sx={{ marginTop: 3 }}
        onClick={() => navigate("/employees")} // Adjust the path if necessary
      >
        Back to Employee List
      </Button>
    </Box>
  );
};

export default DeletedEmployeeList;
