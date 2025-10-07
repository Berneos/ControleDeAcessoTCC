package com.grandesabegos.ControleDeAcessoTCC.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET = "umaChaveMuitoGrandeParaSeguranca123456789"; 
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hora

    public static String gerarToken(String usuario) {
        Instant now = Instant.now();
        return Jwts.builder()
            .subject(usuario)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plusSeconds(3600))) // 1 hora
            .signWith(SECRET_KEY)
            .compact();
    }


    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = getUsername(token);
            if (!username.equals(userDetails.getUsername())) {
                return false;
            }
            // Se tiver data de expiração, checar isso
            // extractClaims(token).getExpiration() > now etc
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
