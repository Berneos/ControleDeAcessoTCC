package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

public class PessoaMinDTO {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Instant dataCadastro;
	private String telefone;
	
	public PessoaMinDTO(Pessoa entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		ativo = entity.getAtivo();
		dataCadastro = entity.getDataCadastro();
		telefone = entity.getTelefone();
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Boolean getAtivo() {return ativo;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getTelefone() {return telefone;}
	
	
	
}
