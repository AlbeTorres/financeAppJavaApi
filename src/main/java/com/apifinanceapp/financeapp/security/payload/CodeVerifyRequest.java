package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CodeVerifyRequest {
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "2FA Code is required")
    private String verificationCode;
}
