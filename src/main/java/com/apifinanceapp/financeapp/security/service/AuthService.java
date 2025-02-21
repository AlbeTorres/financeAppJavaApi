package com.apifinanceapp.financeapp.security.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.mappers.UserMapper;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.common.Role;
import com.apifinanceapp.financeapp.repository.UserRepository;
import com.apifinanceapp.financeapp.security.jwt.JWTService;
import com.apifinanceapp.financeapp.security.model.PasswordToken;
import com.apifinanceapp.financeapp.security.model.UserPrincipal;
import com.apifinanceapp.financeapp.security.model.VerificationToken;
import com.apifinanceapp.financeapp.security.payload.UserCreateRequest;
import com.apifinanceapp.financeapp.security.payload.UserCreateResponse;
import com.apifinanceapp.financeapp.security.repository.PasswordTokenRepository;
import com.apifinanceapp.financeapp.security.repository.VerificationTokenRepository;

@Service
public class AuthService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordTokenRepository passwordTokenRepository;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthEmailService emailService;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
            JWTService jwtService, BCryptPasswordEncoder passwordEncoder,
            VerificationTokenRepository verificationTokenRepository, AuthEmailService emailService,
            PasswordTokenRepository passwordTokenRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.passwordTokenRepository = passwordTokenRepository;
    }

    public UserCreateResponse registerUser(UserCreateRequest userData) {

        // Validar que el user enviado contenga los campos requeridos
        if (userData.getEmail() == null || userData.getPassword() == null || userData.getName() == null
                || userData.getUsername() == null) {
            throw new RuntimeException("Invalid fields");
        }

        // Validar que el email no exista en la base de datos
        if (userRepository.findByEmail(userData.getEmail()).isPresent()) {
            throw new RuntimeException("Email alredy used");
        }

        User user = new User();
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setName(userData.getName());
        user.setUsername(userData.getUsername());
        user.setRole(Role.USER);

        User dbUser = userRepository.save(user);

        // Generar el token de verificación
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationToken(user.getEmail()));
        verificationToken.setEmail(dbUser.getEmail());

        // Guardar el token de verificación
        verificationTokenRepository.save(verificationToken);

        // enviar email con el token de verificación
        emailService.sendVerificationEmail(dbUser.getEmail(), verificationToken.getToken());

        String jwToken = loginUser(dbUser.getEmail(), userData.getPassword());

        // TODO generar dto para devolver user y token al registrarse

        UserCreateResponse response = new UserCreateResponse(userMapper.toResponse(dbUser), jwToken);

        return response;

    }

    public String loginUser(String email, String password) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(email);

        }
        return "Invalid Credentials";
    }

    public String verifyUser(String token) {

        String email = getAuthenticatedUsername();

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid Token"));

        if (!verificationToken.getEmail().equals(email)) {
            throw new RuntimeException("User not authorized");
        }

        if (jwtService.validateToken(token, verificationToken.getEmail())) {

            User user = userRepository.findByEmail(verificationToken.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Date now = new Date();
            LocalDateTime localDateTime = now.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            user.setEmailVerified(localDateTime);

            userRepository.save(user);

            verificationTokenRepository.delete(verificationToken);

            return "User Verified";

        } else {
            throw new RuntimeException("Invalid Token");
        }

    }

    public String resendVerificationToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        VerificationToken verificationToken = verificationTokenRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        if (user.getEmailVerified() != null) {
            return "User already verified";
        }

        verificationToken.setToken(jwtService.generateVerificationToken(email));

        verificationTokenRepository.save(verificationToken);

        // enviar email con el token de verificación
        emailService.sendVerificationEmail(email, verificationToken.getToken());

        return "Token Resent";
    }

    public String forgotPassword(String email) {

        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        PasswordToken passwordToken = new PasswordToken();

        String token = generateSecureToken();
        passwordToken.setToken(token);
        passwordToken.setEmail(email);

        passwordTokenRepository.save(passwordToken);

        // enviar email con el token de verificación
        emailService.sendVerificationEmail(email, passwordToken.getToken());

        return "Email Sent";
    }

    public String resetPassword(String token, String oldPassword, String newPassword) {

        PasswordToken passwordToken = passwordTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid Token"));

        User user = userRepository.findByEmail(passwordToken.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            passwordTokenRepository.delete(passwordToken);

            return "Password Changed";
        } else {
            return "Invalid Password";
        }
    }

    public static String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom(); // No necesita manejo de excepciones
        byte[] tokenBytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getEmail();
        } else {
            return principal.toString(); // En caso de ser solo un string (ej: token JWT)
        }
    }

}
