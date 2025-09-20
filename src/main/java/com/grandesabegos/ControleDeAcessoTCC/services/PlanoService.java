package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PlanoRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
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
	
	public Plano insert(Plano obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			Plano obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	
	}
	 
	public Plano update(Long id, Plano obj) {
		try {
			Plano entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Plano entity, Plano obj) {
		entity.setNome(obj.getNome());
		entity.setDescricao(obj.getDescricao());
		entity.setPreco(obj.getPreco());
	}
	
	public List<Plano> findByEmpresaId(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }
}

