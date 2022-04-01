package pro.sky.java.lesson24.webemployeetest22.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.lesson24.webemployeetest22.service.DepartmentService;
import pro.sky.java.lesson24.webemployeetest22.service.Employee;


import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/show")
    public List<Employee> showAllEmployeesInDepartment(@RequestParam("numberDepartment") int numberDepartment) {
        return departmentService.showAllEmployeesInDepartment(numberDepartment);
    }

    @GetMapping("/sum-all-salary")
    public int sumAllSalaryInDepartment(@RequestParam("numberDepartment") int numberDepartment) {
        return departmentService.sumAllSalaryInDepartment(numberDepartment);
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalaryInDepartment(@RequestParam("numberDepartment") int numberDepartment) {
        return departmentService.employeeWithMaxSalaryInDepartment(numberDepartment);
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalaryInDepartment(@RequestParam("numberDepartment") int numberDepartment) {
        return departmentService.employeeWithMinSalaryInDepartment(numberDepartment);
    }

    @GetMapping("/middle-salary")
    public double middleSalaryInDepartment(@RequestParam("numberDepartment") int numberDepartment) {
        return departmentService.middleSalaryInDepartment(numberDepartment);
    }

    @GetMapping("/multiple")
    public void multipleSalaryInDepartment(@RequestParam("multiple") float multiple,
                                           @RequestParam("numberDepartment") int numberDepartment) {
        departmentService.multipleSalaryInDepartment(multiple, numberDepartment);
    }
}
