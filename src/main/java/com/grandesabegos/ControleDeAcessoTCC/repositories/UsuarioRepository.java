package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;

public interface UsuarioRepository extends JpaRepository<Responsavel, Long>{

}
