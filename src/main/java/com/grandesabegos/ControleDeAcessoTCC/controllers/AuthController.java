package com.grandesabegos.ControleDeAcessoTCC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandesabegos.ControleDeAcessoTCC.dto.LoginRequest;
import com.grandesabegos.ControleDeAcessoTCC.dto.LoginResponse;
import com.grandesabegos.ControleDeAcessoTCC.security.JwtUtil;
import com.grandesabegos.ControleDeAcessoTCC.security.UserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil; // ✅ injeta a instância configurada com @Value

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Recupera UserDetails autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // ✅ Gera token JWT usando a instância injetada
            String token = jwtUtil.gerarToken(userDetails.getUsername());
            

            // Cria DTO de resposta
            LoginResponse response = new LoginResponse(
                    token,
                    userDetails.getUsername(),
                    userDetails.getAuthorities().iterator().next().getAuthority(),
                    "Login realizado com sucesso!"
            );

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, null, null, "Falha no login: " + e.getMessage()));
        }
    }
}
