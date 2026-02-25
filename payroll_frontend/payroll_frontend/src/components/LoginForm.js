// src/components/LoginForm.js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Box,
  TextField,
  Typography,
  Button,
  Paper,
  Grid,
  Divider,
} from "@mui/material";
import { motion } from "framer-motion";

const LoginForm = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials({ ...credentials, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (credentials.username === "admin" && credentials.password === "password") {
      navigate("/employees");
    } else {
      alert("Invalid credentials");
    }
  };

  return (
    <Grid
      container
      component="main"
      sx={{
        height: "100vh",
        background: "linear-gradient(to right, #6a11cb, #2575fc)",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <motion.div
        initial={{ opacity: 0, scale: 0.8 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.8 }}
      >
        <Paper
          elevation={6}
          sx={{
            padding: 4,
            borderRadius: 4,
            maxWidth: 400,
            textAlign: "center",
          }}
        >
          <Typography
            component="h1"
            variant="h4"
            sx={{
              fontWeight: "bold",
              marginBottom: 2,
              background: "-webkit-linear-gradient(#6a11cb, #2575fc)",
              WebkitBackgroundClip: "text",
              WebkitTextFillColor: "transparent",
            }}
          >
            PAYROLL MANAGEMENT SYSTEM LOGIN
          </Typography>
         
          <form onSubmit={handleSubmit}>
            <TextField
              fullWidth
              variant="outlined"
              label="Username"
              name="username"
              value={credentials.username}
              onChange={handleChange}
              sx={{ marginBottom: 2 }}
              InputProps={{
                style: { borderRadius: "8px" },
              }}
            />
            <TextField
              fullWidth
              variant="outlined"
              label="Password"
              name="password"
              type="password"
              value={credentials.password}
              onChange={handleChange}
              sx={{ marginBottom: 2 }}
              InputProps={{
                style: { borderRadius: "8px" },
              }}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{
                marginTop: 2,
                padding: 1,
                borderRadius: "8px",
                background: "linear-gradient(to right, #6a11cb, #2575fc)",
                "&:hover": {
                  background: "linear-gradient(to right, #2575fc, #6a11cb)",
                },
              }}
            >
              Log In
            </Button>
          </form>
          <Divider sx={{ marginY: 3 }} />
          <Box display="flex" justifyContent="center" flexDirection="column" alignItems="center" mb={2}>
           
            {/* Company Name */}
            <Typography
              variant="body2"
              color="text.primary"
              sx={{
                fontWeight: "bold",
                textTransform: "uppercase",
                fontSize: "16px",
              }}
            >
              Powered by RZSoftwares Pvt. Ltd.
            </Typography>
            
          </Box>
        </Paper>
      </motion.div>
    </Grid>
  );
};

export default LoginForm;
