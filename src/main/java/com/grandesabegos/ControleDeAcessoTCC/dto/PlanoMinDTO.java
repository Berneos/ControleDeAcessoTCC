package com.grandesabegos.ControleDeAcessoTCC.dto;

import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;

public class PlanoMinDTO {

	private Long id;
	private String nome;
	private Double preco;
	private Integer numAssinantes;
	
	public PlanoMinDTO(Plano entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		preco = entity.getPreco();
		numAssinantes = entity.getAssinantes().size();
		
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Double getPreco() {return preco;}

	public Integer getNumAssinantes() {return numAssinantes;}
	
	
	
}
