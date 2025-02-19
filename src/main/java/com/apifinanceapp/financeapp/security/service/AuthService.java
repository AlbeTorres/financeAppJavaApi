package com.apifinanceapp.financeapp.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.common.Role;
import com.apifinanceapp.financeapp.repository.UserRepository;
import com.apifinanceapp.financeapp.security.jwt.JWTService;
import com.apifinanceapp.financeapp.security.model.VerificationToken;
import com.apifinanceapp.financeapp.security.payload.UserCreateRequest;
import com.apifinanceapp.financeapp.security.repository.VerificationTokenRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
            JWTService jwtService, BCryptPasswordEncoder passwordEncoder,
            VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserCreateRequest userData) {

        // Validar que el user enviado contenga los campos requeridos
        if (userData.getEmail() == null || userData.getPassword() == null || userData.getName() == null
                || userData.getUsername() == null) {
            // TODO: throw exception
        }

        // Validar que el email no exista en la base de datos
        if (userRepository.findByEmail(userData.getEmail()).isPresent()) {
            // TODO: throw exception
        }

        User user = new User();
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setName(userData.getName());
        user.setUsername(userData.getUsername());
        user.setRole(Role.USER);

        // TODO: throw exception and
        // Validar que el usuario se haya guardado correctamente
        User dbUser = userRepository.save(user);

        // Generar el token de verificación
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationToken(user.getEmail()));
        verificationToken.setEmail(dbUser.getEmail());

        // Guardar el token de verificación
        verificationTokenRepository.save(verificationToken);

        // enviar email con el token de verificación

        return dbUser;

    }

    public String loginUser(String email, String password) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(email);

        }
        return "Invalid Credentials";
    }

}
