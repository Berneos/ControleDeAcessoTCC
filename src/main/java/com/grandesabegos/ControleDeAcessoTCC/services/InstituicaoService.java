package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository repository;
	
	public List<Instituicao> findAll() {
		
		return repository.findAll();
		
	}
	
	public Instituicao findById(Long id) {
		
		Optional<Instituicao> obj = repository.findById(id);
		return obj.get();
		
	}
	
	public Instituicao insert(Instituicao obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Instituicao obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	
	}
	
	public Instituicao update(Long id, Instituicao obj) {
		try {
			Instituicao entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Instituicao entity, Instituicao obj) {
		entity.setCnpj(obj.getCnpj());
		entity.setNome(obj.getNome());
		entity.setDataCadastro(obj.getDataCadastro());
	}
	
}
