// src/components/TaxForm.js
import React, { useState, useEffect } from 'react';
import { TextField, Grid, Box } from '@mui/material';

const TaxForm = ({ index, tax, handleChange }) => {
    const [calculatedTaxAmount, setCalculatedTaxAmount] = useState('');

    const calculateTaxAmount = (taxableIncome, taxRate) => {
        return (taxableIncome * taxRate) / 100;
    };

    useEffect(() => {
        setCalculatedTaxAmount(calculateTaxAmount(tax.taxableIncome, tax.taxRate));
    }, [tax.taxableIncome, tax.taxRate]);

    return (
        <Box sx={{ mb: 2 }}>
            <h4>Tax Details {index + 1}</h4>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Tax Year"
                        name="taxYear"
                        value={tax.taxYear}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Taxable Income"
                        name="taxableIncome"
                        type="number"
                        value={tax.taxableIncome}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Tax Rate (%)"
                        name="taxRate"
                        type="number"
                        value={tax.taxRate}
                        onChange={(e) => handleChange(e)}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        label="Tax Amount"
                        name="taxAmount"
                        type="number"
                        value={calculatedTaxAmount}
                        disabled
                    />
                </Grid>
            </Grid>
        </Box>
    );
};

export default TaxForm;
