package com.apifinanceapp.financeapp.dto.user;

import com.apifinanceapp.financeapp.model.common.Role;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateRequest {
    private String username;
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    private Role role;
    private String image;

}
