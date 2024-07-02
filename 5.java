import java.util.*;

public class Employee {
    // Private instance variables (attributes)
    private String name;
    private double salary;
    private int age;

    // Constructor
    public Employee(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    // Setter methods with validation (example)
    public void setName(String name) {
        // Perform validation if needed
        this.name = name;
    }

    public void setSalary(double salary) {
        // Perform validation if needed
        if (salary > 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary value");
        }
    }

    public void setAge(int age) {
        // Perform validation if needed
        if (age >= 18) {
            this.age = age;
        } else {
            System.out.println("Employee must be at least 18 years old");
        }
    }

    // Example method demonstrating encapsulation
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Age: " + age);
    }

    // Main method to test encapsulation
    public static void main(String[] args) {
        
        Employee emp = new Employee("John Doe", 50000.0, 30);

        
        emp.displayDetails(); 

        emp.setSalary(60000.0); 
        emp.setAge(32); 

        emp.displayDetails(); 
    }
}



