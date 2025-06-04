package com.grandesabegos.ControleDeAcessoTCC.dto;

import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;

public class CargoMinDTO {

	private Long id;
	private String nome;
	private String setorNome;
	
	public CargoMinDTO(Cargo entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		setorNome = entity.getSetor().getNome();
		
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public String getSetorNome() {return setorNome;}
	
	
	
}
