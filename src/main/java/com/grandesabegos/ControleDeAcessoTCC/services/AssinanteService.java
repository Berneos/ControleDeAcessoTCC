package com.grandesabegos.ControleDeAcessoTCC.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.dto.AssinanteMinDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AssinanteRepository;
import com.grandesabegos.ControleDeAcessoTCC.specifications.AssinanteSpecifications;

@Service
public class AssinanteService extends PessoaService<Assinante> {

	 @Autowired
	    public AssinanteService(AssinanteRepository repo) {
	        this.repository = repo;
	    }
	 
	 public Page<AssinanteMinDTO> filtrar(String nome, Boolean ativo, Instant inicio, Instant fim, String nomePlano, Pageable pageable) {

			Specification<Assinante> spec = Specification.where(AssinanteSpecifications.nomeContem(nome))
			.and(AssinanteSpecifications.ativoIgual(ativo))
			.and(AssinanteSpecifications.dataVencimentoEntre(inicio, fim))
			.and(AssinanteSpecifications.planoIgual(nomePlano));
			
			return repository.findAll(spec, pageable)
			.map(AssinanteMinDTO::new); // usa o construtor do DTO
	 }
	
	    // regras específicas do assinante
	 
	 
}

