package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;

public class FuncionarioMinDTO {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Instant dataCadastro;
	private String cargoNome;
	private String setorNome;
	
	public FuncionarioMinDTO(Funcionario entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		ativo = entity.getAtivo();
		dataCadastro = entity.getDataCadastro();
		cargoNome = entity.getCargo().getNome();
		setorNome = entity.getSetor().getNome();
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Boolean getAtivo() {return ativo;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getCargoNome() {return cargoNome;}
	
	public String getSetorNome() {return setorNome;}
	
	
}
