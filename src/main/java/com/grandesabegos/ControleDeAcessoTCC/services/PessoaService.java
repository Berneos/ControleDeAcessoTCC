package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

public abstract class PessoaService<T extends Pessoa> {

	@Autowired
	protected JpaRepository<T, Long> repository;

	public List<T> findAll() {
		
		return repository.findAll();
		
	}
	
	public T findById(Long id) {
		
		Optional<T> obj = repository.findById(id);
		return obj.get();
		
	}
	
}
