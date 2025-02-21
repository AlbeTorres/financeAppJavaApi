package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordResetRequest {

    @NotEmpty(message = "Token is required")
    private String token;

    @NotEmpty(message = "Old Password is required")
    private String oldPassword;

    @NotEmpty(message = "New Password is required")
    private String newPassword;

}
