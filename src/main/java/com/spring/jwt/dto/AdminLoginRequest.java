package com.spring.jwt.dto;




import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class AdminLoginRequest {

    @Schema(description = "Admin username", example = "admin@example.com")
    private String username;

    @Schema(description = "Admin password", example = "Admin@1234")
    private String password;
}


