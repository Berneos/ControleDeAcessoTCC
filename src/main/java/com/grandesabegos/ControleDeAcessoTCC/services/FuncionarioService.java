package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;
import com.grandesabegos.ControleDeAcessoTCC.repositories.FuncionarioRepository;


@Service
public class FuncionarioService extends PessoaService<Funcionario> {

	 @Autowired
	    public FuncionarioService(FuncionarioRepository repo) {
	        this.repository = repo;
	    }
	
	    // regras específicas do funcionário

	 
}

