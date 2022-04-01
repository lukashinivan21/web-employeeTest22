package pro.sky.java.lesson24.webemployeetest22.service;

import org.springframework.stereotype.Service;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeIsAlreadyInsideListException;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employeeList = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        Employee addingEmployee = new Employee(firstName, lastName, salary, department);
        if (employeeList.contains(addingEmployee)) {
            throw new EmployeeIsAlreadyInsideListException("Employee is already inside list");
        }
        employeeList.add(addingEmployee);
        return addingEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee removingEmployee = new Employee(firstName, lastName, salary, department);
        if (!employeeList.remove(removingEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employeeList.remove(removingEmployee);
        return removingEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        Employee findEmployee = new Employee(firstName, lastName, salary, department);
        if (!employeeList.contains(findEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return findEmployee;
    }

    @Override
    public List<Employee> showAllEmployees() {
        return employeeList.stream()
                .sorted(Comparator.comparing(Employee::getNumberDepartment))
                .collect(Collectors.toList());
    }

    @Override
    public int sumAllSalary() {
        return employeeList.stream()
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public double middleSalary() {
        return employeeList.stream()
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow();
    }

    @Override
    public Employee employeeWithMaxSalary() {
        return employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee employeeWithMinSalary() {
        return employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public void multipleSalary(float multiple) {
        employeeList.forEach(employee -> employee.setSalary((int) (employee.getSalary() * (1 + multiple / 100))));
    }
}
