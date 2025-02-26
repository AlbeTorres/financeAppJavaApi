package com.apifinanceapp.financeapp.security.config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        Map<String, Object> data = new HashMap<>();

        data.put("timestamp", System.currentTimeMillis());
        data.put("status", HttpStatus.FORBIDDEN.value());
        data.put("error", "Forbidden");
        data.put("message", "Access Denied");
        data.put("path", request.getRequestURI());

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();

    }

}
