package com.apifinanceapp.financeapp.security.payload;

import com.apifinanceapp.financeapp.dto.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCreateResponse {

    UserResponse userResponse;
    String token;
}
