package pro.sky.java.lesson24.webemployeetest22.service;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int salary, int department);
    Employee removeEmployee(String firstName, String lastName, int salary, int department);
    Employee findEmployee(String firstName, String lastName, int salary, int department);
    List<Employee> showAllEmployees();
    int sumAllSalary();
    double middleSalary();
    Employee employeeWithMaxSalary();
    Employee employeeWithMinSalary();
    void multipleSalary(float multiple);
}
