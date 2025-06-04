package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;
import com.grandesabegos.ControleDeAcessoTCC.repositories.ResponsavelRepository;

public class ResponsavelService {

	@Autowired
	private ResponsavelRepository repository;
	
	public List<Responsavel> findAll() {
		
		return repository.findAll();
		
	}
	
	public Responsavel findById(Long id) {
		
		Optional<Responsavel> obj = repository.findById(id);
		return obj.get();
		
	}
	

	 
}

