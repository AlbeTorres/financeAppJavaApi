package com.apifinanceapp.financeapp.security.service;

import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.email.service.EmailService;

import jakarta.mail.MessagingException;

@Service
public class AuthEmailService {

    private final EmailService emailService;

    public AuthEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendVerificationEmail(String email, String token) {
        // TODO update con company logo
        String subject = "Verify your email";
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<a style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + "https://localhost" + "/verify/"
                + token + "</a>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendEmail(email, subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }

    }

    public void sendPasswordResetEmail(String email, String token) {
        // TODO update con company logo
        String subject = "Reset your password";

        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Reset your password</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to reset your password:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<a style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + "https://localhost"
                + "/password/"
                + token + "</a>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendEmail(email, subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }

}
