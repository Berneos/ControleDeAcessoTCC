package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;

public class AcessoMinDTO {

	private Long id;
	private Instant dataAcesso;
	
	public AcessoMinDTO(Acesso entity) {
		
		id = entity.getId();
		dataAcesso = entity.getDataAcesso();
		
	}
	
}
