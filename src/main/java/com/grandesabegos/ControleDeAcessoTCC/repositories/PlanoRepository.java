package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;

public interface PlanoRepository extends JpaRepository<Plano, Long>{

    List<Plano> findByEmpresaId(Long empresaId);

	
}
