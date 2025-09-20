package com.grandesabegos.ControleDeAcessoTCC.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.dto.AcessoFilterDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AcessoRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;
import com.grandesabegos.ControleDeAcessoTCC.specifications.AcessoSpecifications;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository repository;
	
	public Page<AcessoFilterDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(AcessoFilterDTO::new);
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
	
	public Page<AcessoFilterDTO> filtrar(Long empresaId, Long pessoaId, Long usuarioId, Long catracaId,
            LocalDate inicio, LocalDate fim, Pageable pageable) {

		Specification<Acesso> spec = Specification
		.where(AcessoSpecifications.empresaIdIgual(empresaId))
		.and(AcessoSpecifications.pessoaIdIgual(pessoaId))
		.and(AcessoSpecifications.usuarioIdIgual(usuarioId))
		.and(AcessoSpecifications.catracaIdIgual(catracaId))
		.and(AcessoSpecifications.dataEntre(inicio, fim));
		
		return repository.findAll(spec, pageable).map(AcessoFilterDTO::new);
	}
	public List<Acesso> findByEmpresaId(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }
	
	
	
}
