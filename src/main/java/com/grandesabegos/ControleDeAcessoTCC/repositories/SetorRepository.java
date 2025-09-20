package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long>{

    List<Setor> findByEmpresaId(Long empresaId);

	
}
