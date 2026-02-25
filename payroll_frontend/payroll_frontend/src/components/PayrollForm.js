// src/components/PayrollForm.js

import React from 'react';
import { TextField, Grid, Box } from '@mui/material';

const PayrollForm = ({ index, payroll, handleChange }) => {
    return (
        <Box sx={{ mb: 2 }}>
            <h4>Payroll Details {index + 1}</h4>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                <TextField
            fullWidth
           label="Salary Date"
           name="salaryDate"
          type="date"
        value={payroll.salaryDate}
      onChange={(e) => handleChange(e)} // Pass index
        InputLabelProps={{ shrink: true }}
/>
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Basic Salary"
                        name="basicSalary"
                        value={payroll.basicSalary}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Allowances"
                        name="allowances"
                        value={payroll.allowances}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Deductions"
                        name="deductions"
                        value={payroll.deductions}
                        onChange={(e) => handleChange(e,index)}
                    />
                </Grid>
            </Grid>
        </Box>
    );
};

export default PayrollForm;
