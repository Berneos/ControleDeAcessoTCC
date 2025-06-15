package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

public abstract class PessoaService<T extends Pessoa> {

	@Autowired
	protected JpaRepository<T, Long> repository;

	public List<T> findAll() {
		
		return repository.findAll();
		
	}
	
	public T findById(Long id) {
		
		Optional<T> obj = repository.findById(id);
		return obj.get();
		
	}
	
	
	public T insert(T obj) {
		
		return repository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		try {
			
			T obj = findById(id);
			repository.delete(obj);
			
		} catch(EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}

	}
	
	public T update(Long id, T obj) {
		try {
			T entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(T entity, T obj) {
		entity.setNome(obj.getNome());
		entity.setAtivo(obj.getAtivo());
		entity.setBiometria(obj.getBiometria());
		entity.setCpf(obj.getCpf());
		entity.setEndereco(obj.getEndereco());
		entity.setFoto(obj.getFoto());
		entity.setTelefone(obj.getTelefone());
	}
	
}
