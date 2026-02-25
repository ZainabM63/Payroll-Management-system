import React, { useState, useEffect } from 'react';
import apiService from '../services/apiService';

function LeaveList() {
  const [leaves, setLeaves] = useState([]);

  useEffect(() => {
    apiService.getLeaves()
      .then(response => {
        setLeaves(response.data);
      })
      .catch(error => {
        console.error('Error fetching leaves:', error);
      });
  }, []);

  return (
    <div>
      <h2>Leave List</h2>
      <table>
        <thead>
          <tr>
            <th>Employee</th>
            <th>Leave Type</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {leaves.map(leave => (
            <tr key={leave.leaveId}>
              <td>{leave.employeeName}</td>
              <td>{leave.leaveType}</td>
              <td>{leave.leaveStatus}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default LeaveList;
