package com.apifinanceapp.financeapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.common.Role;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    User usuario = User.builder().id("1")
            .name("Jhon")
            .email("doe@gmail.com")
            .password("1234")
            .role(Role.USER)
            .emailVerified(LocalDateTime.now())
            .isTwofactorEnabled(true)
            .build();
    User usuario1 = User.builder().id("2")
            .name("Will")
            .email("smith@gmail.com")
            .password("1234")
            .role(Role.USER)
            .emailVerified(LocalDateTime.now())
            .isTwofactorEnabled(true)
            .build();

    List<User> lista = new ArrayList<User>();

    // Este método se ejecutará después de que el servicio haya sido instanciado
    @PostConstruct
    public void populateUserService() {
        lista.add(usuario);
        lista.add(usuario1);
    }

    public List<User> getUsers() {
        return lista;
    }

    public User getUserById(String id) {
        System.out.println(id);
        return lista.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User createUser(User user) {
        System.out.println(user);
        return user;
    }

    public User updateUser(String id, User user) {
        System.out.println("ID: " + id + ", User: " + user);

        // Usar un solo stream para buscar y actualizar
        for (User userr : lista) {
            if (userr.getId().equals(id)) {
                updateFields(userr, user);
                return userr; // Devolver el usuario actualizado inmediatamente
            }
        }

        return null; // Devolver null si no se encontró el usuario
    }

    public String deleteUser(String id) {

        System.out.println(id);

        for (User userr : lista) {
            if (userr.getId().equals(id)) {
                lista.remove(userr);
                return "User satisfactory deleted"; // Salir del bucle si se eliminó el usuario
            }
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
