import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

// Helper function to handle API requests
const apiService = {
  // Employees API
  getEmployees: () => axios.get(`${API_BASE_URL}/employees`),
  getEmployeeById: (id) => axios.get(`${API_BASE_URL}/employees/${id}`),
  createEmployee: (data) => axios.post(`${API_BASE_URL}/employees`, data),
  updateEmployee: (id, data) => axios.put(`${API_BASE_URL}/employees/${id}`, data),
  deleteEmployee: (id) => axios.delete(`${API_BASE_URL}/employees/${id}`),
  
  getDeletedEmployees: () => axios.get(`${API_BASE_URL}/deleted/employees`),
  getDeletedEmployeeById: (id) => axios.get(`${API_BASE_URL}/deleted/employee/${id}/details`),
  getDeletedEmployeesWithDetails: () => axios.get(`${API_BASE_URL}/employees/details`),
    // Other APIs
  getEmployeeDetails: (id) => axios.get(`${API_BASE_URL}/employees/${id}/details`), 
  getLeaves: () => axios.get(`${API_BASE_URL}/leaves`),
  createLeave: (data) => axios.post(`${API_BASE_URL}/leaves`, data),

  // Payrolls API
  getPayrolls: () => axios.get(`${API_BASE_URL}/payrolls`),
  getPayrollById: (id) => axios.get(`${API_BASE_URL}/payrolls/${id}`),
  createPayroll: (data) => axios.post(`${API_BASE_URL}/payrolls`, data),
  addPayroll: (employeeId, data) => axios.post(`${API_BASE_URL}/employees/${employeeId}/payrolls`, data),
  updatePayroll: (id, data) => axios.put(`${API_BASE_URL}/payrolls/${id}`, data),

  // Taxes API
  getTax: () => axios.get(`${API_BASE_URL}/taxes`),
  createTax: (data) => axios.post(`${API_BASE_URL}/taxes`, data),
  updateTax: (id, data) => axios.put(`${API_BASE_URL}/taxes/${id}`, data),  // Added updateTax
  addTax: (data) => axios.post(`${API_BASE_URL}/taxes`, data),  // Added addTax
  
  // Bank Accounts API

    
    getBankAccounts: () => axios.get(`${API_BASE_URL}/bankaccounts`),
    createBankAccount: (data) => axios.post(`${API_BASE_URL}/bankaccounts`, data),
    updateBankAccount: (id, data) => axios.put(`${API_BASE_URL}/bankaccounts/${id}`, data),
    addBankAccount: (employeeId, bankAccount) =>
      axios.post(`${API_BASE_URL}/employees/${employeeId}/bankaccounts`, bankAccount),

  
  // Delete any entity (leave, payroll, tax, bankAccount)
  deleteEntity: (type, id) => {
    let endpoint;
    switch(type) {
      case 'leave':
        endpoint = `/api/leaves/${id}`;
        break;
      case 'payroll':
        endpoint = `/api/payrolls/${id}`;
        break;
      case 'tax':
        endpoint = `/api/taxes/${id}`;
        break;
      case 'bankAccount':
        endpoint = `/api/bankaccounts/${id}`;
        break;
      default:
        throw new Error('Unknown type');
    }
    
    return axios.delete(endpoint);  // Send DELETE request to the appropriate endpoint
  }
};

export default apiService;
