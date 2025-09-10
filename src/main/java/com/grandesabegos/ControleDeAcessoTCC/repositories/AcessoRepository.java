package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Long>, JpaSpecificationExecutor<Acesso>{

}
