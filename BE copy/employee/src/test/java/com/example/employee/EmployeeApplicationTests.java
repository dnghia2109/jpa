package com.example.employee;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Random;

@SpringBootTest
class EmployeeApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Faker faker;

    @Autowired
    private Random rd;

    @Test
    void save_employee() {
        for (int i = 0; i < 25; i++) {
            Employee employee = Employee.builder()
                    .emailAddress(faker.internet().emailAddress())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName()).build();

            employeeRepository.save(employee);
        }
    }

    @Test
    void getEmployeeByEmailAddressAndLastName() {
        List<Employee> list = employeeRepository.getEmployeeByEmailAddressAndLastName("teresa.legros@gmail.com", "Hand");
        list.forEach(System.out::println);
    }

    @Test
    void getDistinctByFirstNameOrLastName() {
        List<Employee> list = employeeRepository.getDistinctByFirstNameOrLastName("Ernestina", "Koch");
        list.stream().forEach(employee -> System.out.println(employee.getFirstName()));
    }

    @Test
    void findByLastNameOrderByFirstNameAsc() {
        List<Employee> list = employeeRepository.findByLastNameOrderByFirstNameAsc("Koch");
        list.stream().forEach(employee -> System.out.println(employee.getFirstName()));
    }

    @Test
    void findByFirstNameIgnoreCase() {
        List<Employee> list = employeeRepository.findByFirstNameIgnoreCase("Koch");
        list.stream().forEach(employee -> System.out.println(employee.getFirstName()));
    }

    @Test
    void sortingAndPaging() {
        Page<Employee> page = employeeRepository.findAll(PageRequest.of(1, 5, Sort.by("firstName").descending()));
        page.forEach(System.out::println);
    }




}
