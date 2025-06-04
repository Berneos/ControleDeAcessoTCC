package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
