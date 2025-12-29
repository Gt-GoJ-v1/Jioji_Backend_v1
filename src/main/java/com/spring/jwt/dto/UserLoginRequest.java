package com.spring.jwt.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginRequest {

    @Schema(description = "Username of the user", example = "john.doe")
    private String username;

    @Schema(description = "Password of the user", example = "Pass@123")
    private String password;
}



