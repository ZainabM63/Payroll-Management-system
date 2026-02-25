// src/components/PayrollList.js

import React, { useState, useEffect } from 'react';
import apiService from '../services/apiService'; // Correctly imported
import { useParams } from 'react-router-dom';

const PayrollList = () => {
  const { id } = useParams();
  const [payrolls, setPayrolls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchEmployeeDetails = async () => {
      try {
        // Correct usage: apiService.getEmployeeDetails
        const response = await apiService.getEmployeeDetails(id);
        setPayrolls(response.data.payrolls || []); // Handle cases where payrolls might be undefined
      } catch (err) {
        setError('Failed to fetch payroll details. Please try again.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    fetchEmployeeDetails();
  }, [id]);

  if (loading) return <p>Loading payroll details...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h3>Payroll Details</h3>
      {payrolls.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Salary Date</th>
              <th>Basic Salary</th>
              <th>Allowances</th>
              <th>Deductions</th>
              <th>Net Salary</th>
            </tr>
          </thead>
          <tbody>
            {payrolls.map((payroll) => (
              <tr key={payroll.payrollId}>
                <td>{payroll.salaryDate}</td>
                <td>{payroll.basicSalary}</td>
                <td>{payroll.allowances}</td>
                <td>{payroll.deductions}</td>
                <td>{payroll.netSalary}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No payroll records found.</p>
      )}
    </div>
  );
};

export default PayrollList;
