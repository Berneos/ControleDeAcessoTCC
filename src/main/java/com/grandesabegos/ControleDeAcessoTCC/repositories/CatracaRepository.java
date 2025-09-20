package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;

public interface CatracaRepository extends JpaRepository<Catraca, Long>{

    List<Catraca> findByEmpresaId(Long empresaId);

	
}
