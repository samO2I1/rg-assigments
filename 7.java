import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
    // JDBC URL, username and password of MySQL server
    private static final String JDBC_URL = "jdbc:sqlite:test.db";
    private static final String JDBC_USER = "";
    private static final String JDBC_PASSWORD = "";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;

    // Initialize the connection
    static {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create operation: Add new employee
    public static void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());
            statement.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read operation: Get all employees
    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, name, department FROM employees";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                Employee employee = new Employee(id, name, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Read operation: Get employee by ID
    public static Employee getEmployeeById(int id) {
        String sql = "SELECT id, name, department FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                return new Employee(id, name, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Employee with given ID not found
    }

    // Update operation: Update employee details
    public static void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation: Delete employee by ID
    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test CRUD operations
    public static void main(String[] args) {
        // Test CRUD operations
        Employee emp1 = new Employee(1, "John Doe", "IT");
        Employee emp2 = new Employee(2, "Jane Smith", "HR");

        // Create operation: Add new employees
        addEmployee(emp1);
        addEmployee(emp2);

        // Read operation: Get all employees
        List<Employee> allEmployees = getAllEmployees();
        System.out.println("All Employees:");
        for (Employee emp : allEmployees) {
            System.out.println(emp.getId() + " | " + emp.getName() + " | " + emp.getDepartment());
        }

        // Read operation: Get employee by ID
        Employee employeeById = getEmployeeById(1);
        if (employeeById != null) {
            System.out.println("\nEmployee with ID 1:");
            System.out.println(employeeById.getId() + " | " + employeeById.getName() + " | " + employeeById.getDepartment());
        } else {
            System.out.println("\nEmployee with ID 1 not found.");
        }

        // Update operation: Update employee details
        Employee updatedEmployee = new Employee(2, "Jane Doe", "Finance");
        updateEmployee(updatedEmployee);

        // Read again after update
        System.out.println("\nUpdated Employee details:");
        Employee updatedEmployeeResult = getEmployeeById(2);
        if (updatedEmployeeResult != null) {
            System.out.println(updatedEmployeeResult.getId() + " | " + updatedEmployeeResult.getName() + " | " + updatedEmployeeResult.getDepartment());
        }

        // Delete operation: Delete employee by ID
        deleteEmployee(1);
        System.out.println("\nAfter deleting employee with ID 1:");
        allEmployees = getAllEmployees();
        for (Employee emp : allEmployees) {
            System.out.println(emp.getId() + " | " + emp.getName() + " | " + emp.getDepartment());
        }
    }
}




