package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TokenVerificationRequest {

    @NotEmpty(message = "Verification Code is required")
    private String verificationCode;
}
