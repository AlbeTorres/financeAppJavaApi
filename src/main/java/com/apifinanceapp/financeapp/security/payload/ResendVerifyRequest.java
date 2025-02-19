package com.apifinanceapp.financeapp.security.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResendVerifyRequest {

    @NotEmpty(message = "Email is required")
    private String email;
}
