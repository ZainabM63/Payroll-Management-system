import axios from "axios";

// Create an Axios instance with baseURL and withCredentials
const api = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
});

const apiService = {
  // Employees API
  getEmployees: () => api.get(`/employees`),
  getEmployeeById: (id) => api.get(`/employees/${id}`),
  createEmployee: (data) => api.post(`/employees`, data),
  updateEmployee: (id, data) => api.put(`/employees/${id}`, data),
  deleteEmployee: (id) => api.delete(`/employees/${id}`),

  getDeletedEmployees: () => api.get(`/deleted/employees`),
  getDeletedEmployeeById: (id) => api.get(`/deleted/employee/${id}/details`),
  getDeletedEmployeesWithDetails: () => api.get(`/employees/details`),
  getEmployeeDetails: (id) => api.get(`/employees/${id}/details`),

  // Leaves
  getLeaves: () => api.get(`/leaves`),
  createLeave: (data) => api.post(`/leaves`, data),

  // Payrolls
  getPayrolls: () => api.get(`/payrolls`),
  getPayrollById: (id) => api.get(`/payrolls/${id}`),
  createPayroll: (data) => api.post(`/payrolls`, data),
  addPayroll: (employeeId, data) => api.post(`/employees/${employeeId}/payrolls`, data),
  updatePayroll: (id, data) => api.put(`/payrolls/${id}`, data),

  // Taxes
  getTax: () => api.get(`/taxes`),
  createTax: (data) => api.post(`/taxes`, data),
  updateTax: (id, data) => api.put(`/taxes/${id}`, data),
  addTax: (data) => api.post(`/taxes`, data),

  // Bank Accounts
  getBankAccounts: () => api.get(`/bankaccounts`),
  createBankAccount: (data) => api.post(`/bankaccounts`, data),
  updateBankAccount: (id, data) => api.put(`/bankaccounts/${id}`, data),
  addBankAccount: (employeeId, bankAccount) => api.post(`/employees/${employeeId}/bankaccounts`, bankAccount),

  // Generic delete
  deleteEntity: (type, id) => {
    let endpoint;
    switch (type) {
      case "leave":
        endpoint = `/leaves/${id}`;
        break;
      case "payroll":
        endpoint = `/payrolls/${id}`;
        break;
      case "tax":
        endpoint = `/taxes/${id}`;
        break;
      case "bankAccount":
        endpoint = `/bankaccounts/${id}`;
        break;
      default:
        throw new Error("Unknown type");
    }
    return api.delete(endpoint); // use instance
  },
};

export default apiService;