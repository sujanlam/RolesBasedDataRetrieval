package com.one_api_for_all.controllers;

import com.one_api_for_all.dtos.EmployeeTypeA;
import com.one_api_for_all.dtos.EmployeeTypeB;
import com.one_api_for_all.dtos.EmployeeTypeC;
import com.one_api_for_all.entity.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    // Sample data
    public EmployeeController() {
        employees.add(new Employee(11, "Alice Smith", "Software Engineer", 5, 20, false, "Engineering", 90000));
        employees.add(new Employee(12, "Bob Johnson", "Product Manager", 8, 15, false, "Product", 110000));
        employees.add(new Employee(13,"Charlie Brown", "UX Designer", 3, 25, true, "Design", 75000));
        employees.add(new Employee(14,"Diana Prince", "QA Engineer", 2, 10, false, "Quality Assurance", 80000));
        employees.add(new Employee(15, "Evan Wright", "Data Scientist", 6, 30, false, "Data Science", 120000));
    }

    @GetMapping
    public List<?> getEmployees(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println(role);
        switch (role){
            case "ROLE_A":
                return employees.stream()
                        .map(e -> new EmployeeTypeA(e.getId(), e.getName(), e.getPosition(), e.getYearsOfExperience(), e.getPTOs()))
                        .collect(Collectors.toList());
            case "ROLE_B":
                return employees.stream()
                        .map(e -> new EmployeeTypeB(e.getId(), e.getDepartment()))
                        .collect(Collectors.toList());
            case "ROLE_C":
                return employees.stream()
                        .map(e -> new EmployeeTypeC(e.getId(), e.getSalary()))
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Invalid Role: "+role);
        }
    }
}

