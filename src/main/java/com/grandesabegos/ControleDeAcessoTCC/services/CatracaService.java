package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;
import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.repositories.CatracaRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
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
	
	public Catraca insert(Catraca obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Catraca obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	
	}
	
	public Catraca update(Long id, Catraca obj) {
		try {
			Catraca entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Catraca entity, Catraca obj) {
		entity.setNome(obj.getNome());
	}
	 
	public List<Catraca> findByEmpresaId(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }
	
	public List<Catraca> findByNome(String nome) {
	    return repository.findByNomeContainingIgnoreCase(nome);
	}

	
}

