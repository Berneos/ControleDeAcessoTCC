package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

    List<Cargo> findByEmpresaId(Long empresaId);

	
}
