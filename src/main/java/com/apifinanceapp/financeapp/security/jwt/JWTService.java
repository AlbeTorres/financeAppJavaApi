package com.apifinanceapp.financeapp.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
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

    // Genera el jwt
    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(email).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).and().signWith(getKey())
                .compact();

    }

    // Valida el jwt
    public boolean validateToken(String token, String email) {
        final String emailToken = extractEmail(token);
        return (emailToken.equals(email) && !isTokenExpired(token));
    }

    public SecretKey getKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
