package com.apifinanceapp.financeapp.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.apifinanceapp.financeapp.dto.user.UserCreateRequest;
import com.apifinanceapp.financeapp.dto.user.UserResponse;
import com.apifinanceapp.financeapp.dto.user.UserUpdateRequest;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.common.Role;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .image(user.getImage())
                .isTwofactorEnabled(user.isTwofactorEnabled())
                .emailVerified(user.getEmailVerified())
                .build();
    }

    public User toEntity(UserCreateRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword()) // Nota: esto debe ser encriptado en el servicio
                .role(Role.USER)
                .isTwofactorEnabled(false)
                .build();
    }

    public void updateEntity(User user, UserUpdateRequest request) {
        Optional.ofNullable(request.getName()).ifPresent(user::setName);
        Optional.ofNullable(request.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(request.getImage()).ifPresent(user::setImage);
        Optional.ofNullable(request.getRole()).ifPresent(user::setRole);
        // No actualizamos campos sensibles como password aquí
    }
}
