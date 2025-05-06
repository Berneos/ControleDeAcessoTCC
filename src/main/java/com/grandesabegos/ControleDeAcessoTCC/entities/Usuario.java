package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	private String cpf;
	private String telefone;
	private Boolean isAdmin;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Instituicao empresa;
	private String username;
	private String email;
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private Set<Acesso> acessos = new HashSet<>();
	
	public Usuario(Long id, String nome, String cpf, String telefone, Boolean isAdmin, Instant dataCadastro, Instituicao empresa,
			String username, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.isAdmin = isAdmin;
		this.dataCadastro = dataCadastro;
		this.empresa = empresa;
		this.username = username;
		this.email = email;
		this.senha = senha;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}

	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}

	public Boolean getIsAdmin() {return isAdmin;}
	public void setIsAdmin(Boolean isAdmin) {this.isAdmin = isAdmin;}

	public Instant getDataCadastro() {return dataCadastro;}
	public void setDataCadastro(Instant dataCadastro) {this.dataCadastro = dataCadastro;}

	public Instituicao getEmpresa() {return empresa;}
	public void setEmpresa(Instituicao empresa) {this.empresa = empresa;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getSenha() {return senha;}
	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, empresa, isAdmin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(empresa, other.empresa) && Objects.equals(isAdmin, other.isAdmin);
	}

	public void setSenha(String senha) {this.senha = senha;}
	
}
