// src/components/BankAccountForm.js
import React from 'react';
import { TextField, Grid, Box } from '@mui/material';

const BankAccountForm = ({ index, account, handleChange }) => {
    return (
        <Box sx={{ mb: 2 }}>
            <h4>Bank Account Details {index + 1}</h4>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Bank Name"
                        name="bankName"
                        value={account.bankName}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Account Number"
                        name="accountNumber"
                        value={account.accountNumber}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
            </Grid>
        </Box>
    );
};

export default BankAccountForm;
