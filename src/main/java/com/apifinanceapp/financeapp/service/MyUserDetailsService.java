package com.apifinanceapp.financeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.UserPrincipal;
import com.apifinanceapp.financeapp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User not found ");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }

}
