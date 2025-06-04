package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
