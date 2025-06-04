package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PlanoRepository;

public class PlanoService {

	@Autowired
	private PlanoRepository repository;
	
	public List<Plano> findAll() {
		
		return repository.findAll();
		
	}
	
	public Plano findById(Long id) {
		
		Optional<Plano> obj = repository.findById(id);
		return obj.get();
		
	}
	

	 
}

