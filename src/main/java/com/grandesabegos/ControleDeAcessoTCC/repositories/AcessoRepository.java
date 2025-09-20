package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

public interface AcessoRepository extends JpaRepository<Acesso, Long>, JpaSpecificationExecutor<Acesso>{

    List<Acesso> findByEmpresaId(Long empresaId);

	
}
