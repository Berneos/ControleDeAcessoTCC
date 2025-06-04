package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;

public class AssinanteMinDTO {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Instant dataCadastro;
	private String planoNome;
	private Instant dataVencimento;
	
	public AssinanteMinDTO(Assinante entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		ativo = entity.getAtivo();
		dataCadastro = entity.getDataCadastro();
		planoNome = entity.getPlano().getNome();
		dataVencimento = entity.getDataVencimento();
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Boolean getAtivo() {return ativo;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getPlanoNome() {return planoNome;}

	public Instant getDataVencimento() {return dataVencimento;}

	
	
	
}
