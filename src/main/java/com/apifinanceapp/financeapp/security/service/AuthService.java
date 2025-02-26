package com.apifinanceapp.financeapp.security.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.mappers.UserMapper;
import com.apifinanceapp.financeapp.model.User;
import com.apifinanceapp.financeapp.model.common.Role;
import com.apifinanceapp.financeapp.repository.UserRepository;

import com.apifinanceapp.financeapp.security.exception.ResponseException;
import com.apifinanceapp.financeapp.security.jwt.JWTService;
import com.apifinanceapp.financeapp.security.model.PasswordToken;
import com.apifinanceapp.financeapp.security.model.TwoFactorCode;
import com.apifinanceapp.financeapp.security.model.UserPrincipal;
import com.apifinanceapp.financeapp.security.model.VerificationToken;
import com.apifinanceapp.financeapp.security.payload.UserCreateRequest;
import com.apifinanceapp.financeapp.security.payload.UserCreateResponse;
import com.apifinanceapp.financeapp.security.repository.PasswordTokenRepository;
import com.apifinanceapp.financeapp.security.repository.TwoFactorCodeRepository;
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

    private final TwoFactorCodeRepository twoFactorCodeRepository;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
            JWTService jwtService, BCryptPasswordEncoder passwordEncoder,
            VerificationTokenRepository verificationTokenRepository, AuthEmailService emailService,
            PasswordTokenRepository passwordTokenRepository, UserMapper userMapper,
            TwoFactorCodeRepository twoFactorCodeRepository) {
        this.twoFactorCodeRepository = twoFactorCodeRepository;
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
            throw new ResponseException(HttpStatus.BAD_REQUEST, "Invalid fields");
        }

        // Validar que la contraseña tenga 8 caracteres minimo, no contega espacios y
        // contenga al menos un número y un caracter especial
        if (!userData.getPassword()
                .matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")) {
            throw new ResponseException(HttpStatus.BAD_REQUEST,
                    "Password must contain at least 8 characters, one number, one special character and no spaces");
        }

        // Validar que el email no exista en la base de datos
        if (userRepository.findByEmail(userData.getEmail()).isPresent()) {
            throw new ResponseException(HttpStatus.CONFLICT, "Email alredy used");
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

        UserCreateResponse response = new UserCreateResponse(userMapper.toResponse(dbUser), jwToken);

        return response;

    }

    public String loginUser(String email, String password) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (auth.isAuthenticated()) {
            // Obtén el usuario autenticado desde el Authentication
            UserPrincipal authUser = (UserPrincipal) auth.getPrincipal();

            if (authUser.getIsTwoFactorEnabled()) {
                // Generar código de verificación
                String code = generateDigitCode();

                // Guardar el código en la base de datos
                TwoFactorCode twoFactorCode = new TwoFactorCode();
                twoFactorCode.setCode(code);
                twoFactorCode.setEmail(authUser.getEmail());
                twoFactorCode.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 5));
                twoFactorCodeRepository.save(twoFactorCode);

                // Enviar código de verificación al usuario
                emailService.sendTwoFactorCodeEmail(authUser.getEmail(), code);

                // Respuesta temporal (no JWT final aún)
                return "2FA_REQUIRED:" + authUser.getEmail();

            }

            return jwtService.generateToken(email);

        }
        throw new ResponseException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
    }

    public String verify2FA(String email, String code) {
        TwoFactorCode twoFactorCode = twoFactorCodeRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Code not found"));

        if (twoFactorCode.getExpiryDate().before(new Date())) {
            twoFactorCodeRepository.delete(twoFactorCode);
            throw new ResponseException(HttpStatus.UNAUTHORIZED, "Code Expired");
        }

        if (twoFactorCode.getCode().equals(code)) {
            twoFactorCodeRepository.delete(twoFactorCode);
            return jwtService.generateToken(email);
        } else {
            throw new ResponseException(HttpStatus.BAD_REQUEST, "Invalid Code");
        }
    }

    public String verifyUser(String token) {

        String email = getAuthenticatedUsername();

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Invalid Token"));

        if (!verificationToken.getEmail().equals(email)) {
            throw new ResponseException(HttpStatus.FORBIDDEN, "User not authorized");
        }

        if (jwtService.validateToken(token, verificationToken.getEmail())) {

            User user = userRepository.findByEmail(verificationToken.getEmail())
                    .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "User not found"));

            Date now = new Date();
            LocalDateTime localDateTime = now.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            user.setEmailVerified(localDateTime);

            userRepository.save(user);

            verificationTokenRepository.delete(verificationToken);

            return "User Verified";

        } else {
            throw new ResponseException(HttpStatus.BAD_REQUEST, "Invalid Token");
        }

    }

    public String resendVerificationToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "User not found"));

        VerificationToken verificationToken = verificationTokenRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Token not found"));

        if (user.getEmailVerified() != null) {
            throw new ResponseException(HttpStatus.BAD_REQUEST, "User already verified");
        }

        verificationToken.setToken(jwtService.generateVerificationToken(email));

        verificationTokenRepository.save(verificationToken);

        // enviar email con el token de verificación
        emailService.sendVerificationEmail(email, verificationToken.getToken());

        return "Token Resent";
    }

    public String forgotPassword(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "User not found"));

        PasswordToken passwordToken = new PasswordToken();

        String token = generateSecureToken();
        passwordToken.setToken(token);
        passwordToken.setEmail(email);
        passwordToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        passwordTokenRepository.save(passwordToken);

        // enviar email con el token de verificación
        emailService.sendPasswordResetEmail(email, passwordToken.getToken());

        return "Email Sent";
    }

    public String resetPassword(String token, String oldPassword, String newPassword) {

        PasswordToken passwordToken = passwordTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Invalid Token"));

        if (passwordToken.getExpiryDate().before(new Date())) {
            passwordTokenRepository.delete(passwordToken);
            throw new ResponseException(HttpStatus.FORBIDDEN, "Token Expired");
        }

        User user = userRepository.findByEmail(passwordToken.getEmail())
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "User not found"));

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            passwordTokenRepository.delete(passwordToken);

            return "Password Changed";
        } else {
            throw new ResponseException(HttpStatus.FORBIDDEN, "Invalid Password");
        }
    }

    public static String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom(); // No necesita manejo de excepciones
        byte[] tokenBytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public static String generateDigitCode() {
        SecureRandom random = new SecureRandom();
        // Genera un número entre 1000 y 9999 (asegurando 4 dígitos)
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
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
