package com.spring.jwt.controller;


import com.spring.jwt.dto.EmployeeCreateRequest;
import com.spring.jwt.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createEmployee(
            @Valid @RequestBody EmployeeCreateRequest request) {

        employeeService.createEmployee(request);
        return ResponseEntity.ok("Employee created successfully");
    }
}
