package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;
import com.grandesabegos.ControleDeAcessoTCC.entities.Setor;
import com.grandesabegos.ControleDeAcessoTCC.repositories.SetorRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

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
	
	public Setor insert(Setor obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Setor obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	
	}
	

	 
}

