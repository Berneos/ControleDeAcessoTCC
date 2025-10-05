package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;
import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;

public interface CatracaRepository extends JpaRepository<Catraca, Long>, JpaSpecificationExecutor<Catraca>{

    List<Catraca> findByEmpresaId(Long empresaId);

    // Busca catracas cujo nome contenha o termo informado
    List<Catraca> findByNomeContainingIgnoreCase(String nome);
	
}
