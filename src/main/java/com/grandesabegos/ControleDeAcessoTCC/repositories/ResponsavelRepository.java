package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{

	List<Responsavel> findByEstudante_Nome(String nome);

	
}
