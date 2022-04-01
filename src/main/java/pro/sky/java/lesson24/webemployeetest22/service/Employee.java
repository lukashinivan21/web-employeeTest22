package pro.sky.java.lesson24.webemployeetest22.service;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private int salary;
    private int numberDepartment;

    public Employee(String firstName, String lastName, int salary, int numberDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.numberDepartment = numberDepartment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNumberDepartment() {
        return numberDepartment;
    }

    public void setNumberDepartment(int numberDepartment) {
        this.numberDepartment = numberDepartment;
    }

    @Override
    public String toString() {
        return "Полная информация о сотруднике: ФИО: " + firstName + " " + lastName +
                ". Отдел: " + numberDepartment + ". Зарплата: " + salary + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && numberDepartment == employee.numberDepartment && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, numberDepartment);
    }
}
