package com.grandesabegos.ControleDeAcessoTCC.dto;

import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;

public class CatracaMinDTO {

	private Long id;
	private String nome;
	
	
	public CatracaMinDTO(Catraca entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		
	}
	
}
