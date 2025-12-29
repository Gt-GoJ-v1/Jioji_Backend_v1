package com.spring.jwt.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeLoginRequest {

    @Schema(description = "Email of User", example = "employee@example.com")
    private String username;

    @Schema(description = "Password of User", example = "Pass@1234")
    private String password;

    @Schema(description = "Role of User", example = "EMPLOYEE")
    private String role;  // EMPLOYEE / ADMIN
}

