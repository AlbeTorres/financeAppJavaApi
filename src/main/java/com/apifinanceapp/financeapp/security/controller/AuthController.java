package com.apifinanceapp.financeapp.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.security.payload.AuthRequest;
import com.apifinanceapp.financeapp.security.payload.ResendVerifyRequest;
import com.apifinanceapp.financeapp.security.payload.UserCreateRequest;
import com.apifinanceapp.financeapp.security.payload.UserCreateResponse;
import com.apifinanceapp.financeapp.security.payload.VerifyUserEmailRequest;
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
    public UserCreateResponse userRegister(@RequestBody UserCreateRequest user) {
        return authService.registerUser(user);
    }

    @PostMapping("/verify-email")
    public String userEmailVerifier(@RequestBody VerifyUserEmailRequest verifyUserEmailRequest) {
        return authService.verifyUser(verifyUserEmailRequest.getVerificationCode());
    }

    @PostMapping("/resend-verification-token")
    public String resendUserEmailVerifier(@RequestBody ResendVerifyRequest resendVerifyRequest) {
        return authService.resendVerificationToken(resendVerifyRequest.getEmail());
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody AuthRequest authenticationRequest) {
        return authService.loginUser(authenticationRequest.getEmail(),
                authenticationRequest.getPassword());

    }

}
