package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;

public class InstituicaoMinDTO {

	private Long id;
	private String nome;
	private String cnpj;
	private Instant dataCadastro;
	private String tipo;
	
	public InstituicaoMinDTO(Instituicao entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		cnpj = entity.getCnpj();
		dataCadastro = entity.getDataCadastro();
		tipo = entity.getTipo().toString();
		
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public String getCnpj() {return cnpj;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getTipo() {return tipo;}
	
	
	
}
