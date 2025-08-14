package com.grandesabegos.ControleDeAcessoTCC.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Chave secreta (mínimo de 256 bits para HS256)
    private static final String SECRET_KEY = "minhaChaveSecretaSuperSeguraComMaisDe32Caracteres!"; 
    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hora

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Gera o token JWT
    public static String gerarToken(String usuario) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey()) // HS256
                .compact();
    }

    // Extrai o username do token
    public String getUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getBody();

            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    // Valida o token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
