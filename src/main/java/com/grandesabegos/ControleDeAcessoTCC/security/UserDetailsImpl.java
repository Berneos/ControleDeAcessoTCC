package com.grandesabegos.ControleDeAcessoTCC.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // se for master, dá MASTER (e opcionalmente ADMIN também)
        if (Boolean.TRUE.equals(usuario.getIsMaster())) {
            return Set.of(
                new SimpleGrantedAuthority("ROLE_MASTER"),
                new SimpleGrantedAuthority("ROLE_ADMIN") // opcional, se master também for admin
            );
        }
        if (Boolean.TRUE.equals(usuario.getIsAdmin())) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }



    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Long getId() {
    	
    	return usuario.getId();
    	
    }
    
    
}
