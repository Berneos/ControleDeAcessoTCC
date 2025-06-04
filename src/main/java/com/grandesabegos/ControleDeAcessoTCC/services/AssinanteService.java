package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.repositories.AssinanteRepository;

public class AssinanteService extends PessoaService<Assinante> {

	 @Autowired
	    public AssinanteService(AssinanteRepository repo) {
	        this.repository = repo;
	    }
	
	    // regras específicas do assinante

	 
}

