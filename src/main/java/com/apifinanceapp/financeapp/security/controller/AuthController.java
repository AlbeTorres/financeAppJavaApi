package com.apifinanceapp.financeapp.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.security.payload.AuthRequest;
import com.apifinanceapp.financeapp.security.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User userRegister(@RequestBody User user) {
        System.out.println("hola mundo register");
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody AuthRequest authenticationRequest) {
        return authService.loginUser(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

    }

}
