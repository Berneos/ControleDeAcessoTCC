package com.grandesabegos.ControleDeAcessoTCC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public class PessoaCreateDTO {
	private Long id; // opcional (null para criar)

	@NotBlank
	@Size(max = 200)
	private String nome;

	@Size(max = 20)
	private String cpf;

	@Size(max = 30)
	private String telefone;

	// biometria (opcional)
	private String biometria;

	// se quiser já controlar ativo no cadastro
	private Boolean ativo = true;

	// se a API aceitar data de cadastro enviada; senão service define
	private Instant dataCadastro;

	// somente o id da empresa (o que você pediu)
	private Long empresaId;

	// getters / setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBiometria() {
		return biometria;
	}

	public void setBiometria(String biometria) {
		this.biometria = biometria;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
}
