package com.apifinanceapp.financeapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.dto.user.UserCreateRequest;
import com.apifinanceapp.financeapp.dto.user.UserResponse;
import com.apifinanceapp.financeapp.mappers.UserMapper;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());

    }

    public UserResponse getUserById(String id) {

        // TODO: Añadir excepciones para los errores ver video de manejo de excepciones

        // Validar el id (puedes usar una expresión regular o simplemente comprobar que
        // no esté vacío)
        if (id == null || id.trim().isEmpty()) {
            // TODO: Añadir excepciones para los errores ver video de manejo de excepciones
            return null;
        }

        // Si el id es un UUID, por ejemplo, puedes validarlo con una expresión regular
        if (!id.matches("^[a-fA-F0-9-]{36}$")) { // Si es un UUID
            // TODO: Añadir excepciones para los errores ver video de manejo de excepciones
            return null;
        }

        User user = userRepository.findById(id).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }

    public UserResponse createUser(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        // TODO revizar si esto es lo mismo que bcrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public User updateUser(String id, User user) {

        User target = userRepository.findById(id).orElse(null);

        if (user != null) {
            updateFields(target, user);
            return userRepository.save(target);
        }
        return null;

    }

    public String deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            userRepository.deleteById(id);
            return "User satisfactory deleted"; // Salir del bucle si se eliminó el usuario

        }

        return "User not found"; // Devolver mensaje de usuario eliminado
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    private void updateFields(User target, User source) {
        // Actualizar solo los campos no nulos
        if (source.getName() != null)
            target.setName(source.getName());
        if (source.getEmail() != null)
            target.setEmail(source.getEmail());
        if (source.getEmailVerified() != null)
            target.setEmailVerified(source.getEmailVerified());
        if (source.getPassword() != null)
            target.setPassword(source.getPassword());
        if (source.getRole() != null)
            target.setRole(source.getRole());
        if (source.getImage() != null)
            target.setImage(source.getImage());
        target.setTwofactorEnabled(source.isTwofactorEnabled()); // Campo booleano siempre se actualiza
    }

    private UserResponse convertToDTO(User user) {
        return new UserResponse(
                user.getId(), // id
                user.getName(), // name
                user.getUsername(), // username
                user.getEmail(), // email
                user.getEmailVerified(), // emailVerified
                user.getRole(), // role
                user.getImage(), // image
                user.isTwofactorEnabled(), // isTwofactorEnabled
                null,
                null,
                null,
                null);
    }

}
