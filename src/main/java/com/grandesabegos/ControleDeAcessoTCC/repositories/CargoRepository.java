package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>, JpaSpecificationExecutor<Cargo>{

	List<Cargo> findBySetor_Id(Long setorId);

	
}
