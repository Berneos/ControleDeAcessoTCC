package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.repositories.EstudanteRepository;

@Service
public class EstudanteService extends PessoaService<Estudante> {

	 @Autowired
	    public EstudanteService(EstudanteRepository repo) {
	        this.repository = repo;
	    }
	
	    // regras específicas do funcionário

	 
}

