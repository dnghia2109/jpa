package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> getEmployeeByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> getDistinctByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    List<Employee> findByFirstNameIgnoreCase(String firstName);


}