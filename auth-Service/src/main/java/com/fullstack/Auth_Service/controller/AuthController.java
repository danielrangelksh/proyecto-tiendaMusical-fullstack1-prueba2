package com.fullstack.Auth_Service.controller;

import com.fullstack.Auth_Service.dto.LoginRequest;
import com.fullstack.Auth_Service.dto.LoginResponse;
import com.fullstack.Auth_Service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){this.authService = authService;}

    @GetMapping("/publico")
    public ResponseEntity<String> publico() {return ResponseEntity.ok("Auth Service activo");}

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

}
