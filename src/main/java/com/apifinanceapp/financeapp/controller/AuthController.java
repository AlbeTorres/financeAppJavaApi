package com.apifinanceapp.financeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.CredentialDTO;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User userRegister(@RequestBody User user) {

        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody CredentialDTO credentialDTO) {
        return authService.loginUser(credentialDTO.getUsername(), credentialDTO.getPassword());
    }

}
