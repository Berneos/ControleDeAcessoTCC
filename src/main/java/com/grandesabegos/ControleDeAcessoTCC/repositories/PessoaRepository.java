package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
