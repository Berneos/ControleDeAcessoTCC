package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;
import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;
import com.grandesabegos.ControleDeAcessoTCC.repositories.ResponsavelRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	public Responsavel insert(Responsavel obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Responsavel obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	
	}
	
	public Responsavel update(Long id, Responsavel obj) {
		try {
			Responsavel entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Responsavel entity, Responsavel obj) {
		entity.setNome(obj.getNome());
		entity.setEmails(obj.getEmails());
		entity.setTelefone(obj.getTelefone());
	}

	 
}

