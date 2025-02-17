package com.apifinanceapp.financeapp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.repository.UserRepository;
import com.apifinanceapp.financeapp.security.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // User user = userRepository.findByUsername(username).orElseThrow(() -> new
        // UsernameNotFoundException("Usuario no encontrado"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No se encontró usuario con el identificador: " + email));

        return new UserPrincipal(user);
    }

}
