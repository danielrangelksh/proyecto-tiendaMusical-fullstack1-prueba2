package com.fullstack.Auth_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String mensaje;
    private String token;
    private String username;
    private String rol;
}
