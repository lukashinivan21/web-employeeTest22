package pro.sky.java.lesson24.webemployeetest22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.lesson24.webemployeetest22.exceptions.EmployeeNotFoundException;
import pro.sky.java.lesson24.webemployeetest22.exceptions.NumberDepartmentIsNotCorrectException;
import pro.sky.java.lesson24.webemployeetest22.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final EmployeeService employeeServiceMock = mock(EmployeeServiceImpl.class);

    private DepartmentService out;

    @BeforeEach
    public void initOut() {
        out = new DepartmentServiceImpl(employeeServiceMock);
    }

    private String name1, name2;
    private String surname1, surname2;
    private int salary1, salary2;
    private int numDep1, numDep2;
    private Employee employee1, employee2;
    private final List<Employee> actualList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        name1 = "Максим";
        name2 = "Игорь";
        surname1 = "Бородин";
        surname2 = "Васильев";
        salary1 = 84300;
        salary2 = 78600;
        numDep1 = 2;
        numDep2 = 4;
        employee1 = new Employee(name1, surname1, salary1, numDep1);
        employee2 = new Employee(name2, surname2, salary2, numDep1);
        actualList.add(employee1);
        actualList.add(employee2);
        Assertions.assertNotNull(employeeServiceMock);
        Mockito.when(employeeServiceMock.showAllEmployees()).thenReturn(actualList);
    }

    @Test
    public void showAllEmployeesOfDepartment() {
        List<Employee> expected = out.showAllEmployeesInDepartment(numDep1);
        Assertions.assertEquals(expected, actualList);
    }

    @Test
    public void showAllEmployeesOfDepartmentIfMainListNotContainsNumberDepartment() {
        List<Employee> emptyList = new ArrayList<>();
        List<Employee> expected = out.showAllEmployeesInDepartment(4);
        Assertions.assertEquals(expected, emptyList);
    }

    @Test
    public void sumAllSalaryInDepartment() {
        int expected = out.sumAllSalaryInDepartment(numDep1);
        int actual = employee1.getSalary() + employee2.getSalary();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sumAllSalaryInDepartmentIfMainListNotContainsNumberDepartment() {
        int expected = out.sumAllSalaryInDepartment(8);
        int actual = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void EmployeeWithMaxSalaryInDepartment() {
        Employee expected = out.employeeWithMaxSalaryInDepartment(numDep1);
        Employee actual = employee1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void EmployeeWithMaxSalaryThrowException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMaxSalaryInDepartment(5));
    }

    @Test
    public void EmployeeWithMinSalaryInDepartment() {
        Employee expected = out.employeeWithMinSalaryInDepartment(numDep1);
        Employee actual = employee2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void EmployeeWithMinSalaryThrowException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.employeeWithMinSalaryInDepartment(5));
    }

    @Test
    public void middleSalaryInDepartment() {
        double expected = out.middleSalaryInDepartment(numDep1);
        double actual = (double) (employee1.getSalary() + employee2.getSalary()) / actualList.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void middleSalaryInDepartmentThrowException() {
        Assertions.assertThrows(NumberDepartmentIsNotCorrectException.class, () -> out.middleSalaryInDepartment(5));
    }

    @Test
    public void multipleSalaryInDepartment() {
        float multiple = 11;
        out.multipleSalaryInDepartment(multiple, numDep1);
        List<Employee> expected = out.showAllEmployeesInDepartment(numDep1);
        for (Employee employee : actualList) {
            employee.setSalary((int) (employee.getSalary() * (1 + multiple / 100)));
        }
        Assertions.assertEquals(expected, actualList);
    }

    @Test
    public void multipleSalaryInDepartmentThrowException() {
        float multiple = 7;
        int numDepartment = 8;
        Assertions.assertThrows(NumberDepartmentIsNotCorrectException.class, () -> out.multipleSalaryInDepartment(multiple, numDepartment));
    }


}
