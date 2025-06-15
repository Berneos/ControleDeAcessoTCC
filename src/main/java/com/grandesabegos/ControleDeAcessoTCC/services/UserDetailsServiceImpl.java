package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.Optional;

import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;
import com.grandesabegos.ControleDeAcessoTCC.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userOpt = repository.findByUsername(username);
        Usuario user = userOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getSenha())
            .roles(user.getIsAdmin() ? "ADMIN" : "USER")
            .build();
    }
}
