package com.grandesabegos.ControleDeAcessoTCC.dto;

import com.grandesabegos.ControleDeAcessoTCC.entities.Setor;

public class SetorMinDTO {

	private Long id;
	private String nome;
	private Integer numFuncionarios;
	private Integer numCargos;
	
	public SetorMinDTO(Setor entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		numFuncionarios = entity.getFuncionario().size();
		numCargos = entity.getCargos().size();		
	}
	
}
