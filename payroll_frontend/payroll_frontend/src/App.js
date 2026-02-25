
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';  // Updated imports
import EmployeeList from './components/EmployeeList';
import EmployeeForm from './components/EmployeeForm';
import LeaveForm from './components/LeaveForm';
import PayrollList from './components/PayrollList';
import PayrollForm from './components/PayrollForm';
import TaxForm from './components/TaxForm';
import BankAccountForm from './components/BankAccountForm';
import LoginForm from './components/LoginForm';
import EmployeeDetails from './components/EmployeeDetails';
import DeletedEmployeeList from './components/DeletedEmployeeList'; // Correct name for listing deleted employees
import DeletedEmployeeDetails from './components/DeletedEmployeeDetails'; // Correct name for viewing deleted employee details
import React from 'react';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/employees/:id/details" element={<EmployeeDetails />} />
          <Route path="/employees" element={<EmployeeList />} />
          <Route path="/employees/new" element={<EmployeeForm />} />
          <Route path="/employees/:id/edit" element={<EmployeeForm />} />
          <Route path="/employees/:id/leaves" element={<LeaveForm />} />
          <Route path="/payrolls" element={<PayrollList />} />
          <Route path="/payrolls/new" element={<PayrollForm />} />
          <Route path="/tax" element={<TaxForm />} />
          <Route path="/bankaccounts" element={<BankAccountForm />} />
          <Route path="/deleted-employees" element={<DeletedEmployeeList />} />
          <Route path="/deleted-employees/:id" element={<DeletedEmployeeDetails />} />

          <Route path="/" element={<LoginForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

