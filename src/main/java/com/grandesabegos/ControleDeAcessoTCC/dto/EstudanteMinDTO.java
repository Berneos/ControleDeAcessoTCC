package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;

public class EstudanteMinDTO {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Instant dataCadastro;
	private String telefone;
	private String responsavelNome;
	
	public EstudanteMinDTO(Estudante entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		ativo = entity.getAtivo();
		dataCadastro = entity.getDataCadastro();
		telefone = entity.getTelefone();
		responsavelNome = entity.getResponsavel().getNome();
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Boolean getAtivo() {return ativo;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getTelefone() {return telefone;}
	
	
	
}
