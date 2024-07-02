import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    private List<Employee> employees;

    public EmployeeCRUD() {
        this.employees = new ArrayList<>();
    }

    // Create operation: Add new employee
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Read operation: Get all employees
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Read operation: Get employee by ID
    public Employee getEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null; // Employee with given ID not found
    }

    // Update operation: Update employee details
    public void updateEmployee(int id, String newName, String newDepartment) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(newName);
                emp.setDepartment(newDepartment);
                return;
            }
        }
        // If employee with given ID is not found, you can throw an exception or handle accordingly
    }

    /



