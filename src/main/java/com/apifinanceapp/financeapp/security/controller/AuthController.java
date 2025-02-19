package com.apifinanceapp.financeapp.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.security.payload.AuthRequest;
import com.apifinanceapp.financeapp.security.payload.UserCreateRequest;
import com.apifinanceapp.financeapp.security.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User userRegister(@RequestBody UserCreateRequest user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody AuthRequest authenticationRequest) {
        return authService.loginUser(authenticationRequest.getEmail(),
                authenticationRequest.getPassword());

    }

}
