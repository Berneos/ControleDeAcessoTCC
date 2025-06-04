package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;
import com.grandesabegos.ControleDeAcessoTCC.repositories.FuncionarioRepository;

public class FuncionarioService extends PessoaService<Funcionario> {

	 @Autowired
	    public FuncionarioService(FuncionarioRepository repo) {
	        this.repository = repo;
	    }
	
	    // regras específicas do funcionário

	 
}

