package pro.sky.java.lesson24.webemployeetest22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeIsAlreadyInsideListException;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeNotFoundException;
import pro.sky.java.lesson24.webemployeetest22.service.Employee;
import pro.sky.java.lesson24.webemployeetest22.service.EmployeeService;
import pro.sky.java.lesson24.webemployeetest22.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    private String name1, name2, name3, name4, name5;
    private String surname1, surname2, surname3, surname4, surname5;
    private int salary1, salary2, salary3, salary4, salary5;
    private int numDep1, numDep2, numDep3, numDep4, numDep5;
    private Employee employee1, employee2, employee3, employee4, employee5;
    private final List<Employee> actualEmployees = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        name1 = "Иван";
        name2 = "Алексей";
        name3 = "Пётр";
        name4 = "Павел";
        name5 = "Олег";
        surname1 = "Петров";
        surname2 = "Сергеев";
        surname3 = "Иванов";
        surname4 = "Смолин";
        surname5 = "Разинов";
        salary1 = 86400;
        salary2 = 75600;
        salary3 = 82500;
        salary4 = 78900;
        salary5 = 81100;
        numDep1 = 1;
        numDep2 = 2;
        numDep3 = 3;
        numDep4 = 4;
        numDep5 = 5;
        employee1 = new Employee(name1, surname1, salary1, numDep1);
        employee2 = new Employee(name2, surname2, salary2, numDep2);
        employee3 = new Employee(name3, surname3, salary3, numDep3);
        employee4 = new Employee(name4, surname4, salary4, numDep4);
        employee5 = new Employee(name5, surname5, salary5, numDep5);
        actualEmployees.add(employee1);
        actualEmployees.add(employee2);
        actualEmployees.add(employee3);
        actualEmployees.add(employee4);
        actualEmployees.add(employee5);
        out.addEmployee(name1, surname1, salary1, numDep1);
        out.addEmployee(name2, surname2, salary2, numDep2);
        out.addEmployee(name3, surname3, salary3, numDep3);
        out.addEmployee(name4, surname4, salary4, numDep4);
        out.addEmployee(name5, surname5, salary5, numDep5);
    }

    @Test
    public void showAllEmployees() {
        List<Employee> expected = out.showAllEmployees();
        Assertions.assertEquals(expected, actualEmployees);
    }

    @Test
    public void addEmployee() {
        List<Employee> expectedEmployees = out.showAllEmployees();
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void removeEmployee() {
        actualEmployees.remove(employee4);
        out.removeEmployee(name4, surname4, salary4, numDep4);
        List<Employee> expected = out.showAllEmployees();
        Assertions.assertEquals(expected, actualEmployees);
    }

    @Test
    public void findEmployee() {
        Employee ourEmployee = out.findEmployee(name1, surname1, salary1, numDep1);
        Assertions.assertTrue(actualEmployees.contains(ourEmployee));
    }

    @Test
    public void addEmployeeThrowException() {
        Assertions.assertThrows(EmployeeIsAlreadyInsideListException.class, () -> out.addEmployee(name3, surname3, salary3, numDep3));
    }

    @Test
    public void removeEmployeeThrowException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee(name1, surname4, salary2, numDep3));
    }

    @Test
    public void findEmployeeThrowException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(name2, surname1, salary5, numDep2));
    }

    @Test
    public void sumSalary() {
        int expected = out.sumAllSalary();
        int result = 0;
        for (Employee actualEmployee : actualEmployees) {
            result += actualEmployee.getSalary();
        }
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void middleSalary() {
        double expected = out.middleSalary();
        int result = 0;
        for (Employee actualEmployee : actualEmployees) {
            result += actualEmployee.getSalary();
        }
        double actual = (double) result / actualEmployees.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void maxSalary() {
        Employee expected = out.employeeWithMaxSalary();
        Employee actual = null;
        int a = 0;
        for (Employee actualEmployee : actualEmployees) {
            if (actualEmployee.getSalary() > a) {
                a = actualEmployee.getSalary();
            }
            if (actualEmployee.getSalary() == a) {
                actual = actualEmployee;
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minSalary() {
        Employee expected = out.employeeWithMinSalary();
        Employee actual = null;
        int a = 1_000_000;
        for (Employee actualEmployee : actualEmployees) {
            if (actualEmployee.getSalary() < a) {
                a = actualEmployee.getSalary();
            }
            if (actualEmployee.getSalary() == a) {
                actual = actualEmployee;
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void multipleSalary() {
        float multiple = 15;
        out.multipleSalary(multiple);
        List<Employee> expected = out.showAllEmployees();
        for (Employee actualEmployee : actualEmployees) {
            int newSalary = (int) (actualEmployee.getSalary() * (1 + multiple / 100));
            actualEmployee.setSalary(newSalary);
        }
        Assertions.assertEquals(expected, actualEmployees);
    }
}
