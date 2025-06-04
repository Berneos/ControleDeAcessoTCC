package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Setor;
import com.grandesabegos.ControleDeAcessoTCC.repositories.SetorRepository;

public class SetorService {

	@Autowired
	private SetorRepository repository;
	
	public List<Setor> findAll() {
		
		return repository.findAll();
		
	}
	
	public Setor findById(Long id) {
		
		Optional<Setor> obj = repository.findById(id);
		return obj.get();
		
	}
	

	 
}

