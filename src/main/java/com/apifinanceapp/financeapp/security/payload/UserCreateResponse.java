package com.apifinanceapp.financeapp.security.payload;

import com.apifinanceapp.financeapp.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCreateResponse {

    User user;
    String token;
}
