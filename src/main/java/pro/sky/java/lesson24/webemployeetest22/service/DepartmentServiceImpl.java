package pro.sky.java.lesson24.webemployeetest22.service;

import org.springframework.stereotype.Service;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeNotFoundException;
import pro.sky.java.lesson24.webemployeetest22.exceptions.NumberDepartmentIsNotCorrectException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> showAllEmployeesInDepartment(int numberDepartment) {
        return employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .collect(Collectors.toList());
    }

    @Override
    public int sumAllSalaryInDepartment(int numberDepartment) {
        return employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Employee employeeWithMaxSalaryInDepartment(int numberDepartment) {
        return employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee employeeWithMinSalaryInDepartment(int numberDepartment) {
        return employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public double middleSalaryInDepartment(int numberDepartment) {
        return employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new NumberDepartmentIsNotCorrectException("Проверьте введённые данные"));

    }

    @Override
    public void multipleSalaryInDepartment(float multiple, int numberDepartment) {
        checkNumberDepartment(numberDepartment);
        employeeService.showAllEmployees().stream()
                .filter(employee -> employee.getNumberDepartment() == numberDepartment)
                .forEach(employee -> employee.setSalary((int) (employee.getSalary() * (1 + multiple / 100))));
    }

    private void checkNumberDepartment(int numberDepartment) {
        List<Integer> numbers = employeeService.showAllEmployees().stream()
                .map(Employee::getNumberDepartment)
                .collect(Collectors.toList());
        if (!numbers.contains(numberDepartment)) {
            throw new NumberDepartmentIsNotCorrectException("Введите корректный номер отдела");
        }
    }
}
