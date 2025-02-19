package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyUserEmailRequest {

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Verification Code is required")
    private String verificationCode;
}
