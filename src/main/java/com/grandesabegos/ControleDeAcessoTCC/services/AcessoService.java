package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AcessoRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository repository;
	
	public List<Acesso> findAll() {
		
		return repository.findAll();
		
	}
	
	public Acesso findById(Long id) {
		
		Optional<Acesso> obj = repository.findById(id);
		return obj.get();
		
	}
	
	public Acesso insert(Acesso obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Acesso acesso = findById(id);
			repository.delete(acesso);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
		
		
		
		
	}
	
}
