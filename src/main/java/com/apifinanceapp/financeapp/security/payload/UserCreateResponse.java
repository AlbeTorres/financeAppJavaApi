package com.apifinanceapp.financeapp.security.payload;

import com.apifinanceapp.financeapp.dto.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateResponse {

    UserResponse userResponse;
    String token;
}
