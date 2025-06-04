package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;
import com.grandesabegos.ControleDeAcessoTCC.repositories.CatracaRepository;

public class CatracaService {

	@Autowired
	private CatracaRepository repository;
	
	public List<Catraca> findAll() {
		
		return repository.findAll();
		
	}
	
	public Catraca findById(Long id) {
		
		Optional<Catraca> obj = repository.findById(id);
		return obj.get();
		
	}
	
	 
}

