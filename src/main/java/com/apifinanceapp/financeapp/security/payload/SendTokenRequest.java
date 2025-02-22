package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SendTokenRequest {

    @NotEmpty(message = "Email is required")
    private String email;
}
