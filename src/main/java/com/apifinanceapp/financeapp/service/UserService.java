package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.User;

import com.apifinanceapp.financeapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
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
}
