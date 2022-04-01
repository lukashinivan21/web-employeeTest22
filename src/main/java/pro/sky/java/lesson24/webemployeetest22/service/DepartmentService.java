package pro.sky.java.lesson24.webemployeetest22.service;

import java.util.List;

public interface DepartmentService {

    List<Employee> showAllEmployeesInDepartment(int numberDepartment);
    int sumAllSalaryInDepartment(int numberDepartment);
    Employee employeeWithMaxSalaryInDepartment(int numberDepartment);
    Employee employeeWithMinSalaryInDepartment(int numberDepartment);
    double middleSalaryInDepartment(int numberDepartment);
    void multipleSalaryInDepartment(float multiple, int numberDepartment);
}
