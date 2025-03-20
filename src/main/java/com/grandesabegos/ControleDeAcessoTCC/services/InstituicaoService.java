package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;

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
	
}
