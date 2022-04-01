package pro.sky.java.lesson24.webemployeetest22.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.lesson24.webemployeetest22.service.Employee;
import pro.sky.java.lesson24.webemployeetest22.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("salary") int salary,
                        @RequestParam("department") int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("salary") int salary,
                           @RequestParam("department") int department) {
        return employeeService.removeEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("salary") int salary,
                         @RequestParam("department") int department) {
        return employeeService.findEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/show")
    public List<Employee> showAllEmployees() {
        return employeeService.showAllEmployees();
    }

    @GetMapping("/sum-all-salary")
    public int sumAllSalary() {
        return employeeService.sumAllSalary();
    }

    @GetMapping("/middle-salary")
    public double middleSalary() {
        return employeeService.middleSalary();
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalary() {
        return employeeService.employeeWithMaxSalary();
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalary() {
        return employeeService.employeeWithMinSalary();
    }

    @GetMapping("/multiple")
    public void multipleSalary(@RequestParam("multiple") float multiple) {
        employeeService.multipleSalary(multiple);
    }
}
