package com.apifinanceapp.financeapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.dto.user.UserCreateRequest;
import com.apifinanceapp.financeapp.dto.user.UserResponse;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {

        Optional<UserResponse> user = Optional.of(userService.getUserById(id));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userRequest) {

        Optional<UserResponse> userDTO = Optional.of(userService.createUser(userRequest));
        return ResponseEntity.ok(userDTO.get());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String value) {

        List<User> users = userService.searchUsers(value);
        return new ResponseEntity<>(users, users.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    // // solo para probar obtener el token csrf
    // @GetMapping("/csrf-token")
    // public CsrfToken getCsrfToken(HttpServletRequest request) {
    // return (CsrfToken) request.getAttribute("_csrf");
    // }

}
