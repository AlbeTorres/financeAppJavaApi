package com.apifinanceapp.financeapp.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private String secretKey;

    public JWTService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("hmacsha256");
            SecretKey ks = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(ks.getEncoded());
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).and().signWith(getKey())
                .compact();

    }

    public Key getKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

}
