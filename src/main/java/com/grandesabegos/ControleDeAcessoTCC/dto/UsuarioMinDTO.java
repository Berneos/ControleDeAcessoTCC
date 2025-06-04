package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;

public class UsuarioMinDTO {

	private Long id;
	private String nome;
	private Boolean isAdmin;
	private Instant dataCadastro;
	private String username;
	
	public UsuarioMinDTO(Usuario entity) {
		
		id = entity.getId();
		nome = entity.getNome();
		isAdmin = entity.getIsAdmin();
		dataCadastro = entity.getDataCadastro();
		username = entity.getUsername();
		
	}

	public Long getId() {return id;}

	public String getNome() {return nome;}

	public Boolean getIsAdmin() {return isAdmin;}

	public Instant getDataCadastro() {return dataCadastro;}

	public String getUsername() {return username;}
	
	
	
}
