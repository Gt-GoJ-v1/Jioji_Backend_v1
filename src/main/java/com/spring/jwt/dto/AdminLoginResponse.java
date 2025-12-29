

package com.spring.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginResponse {
    private String message;
    private String username;
    private String role;
    private String token;
}
