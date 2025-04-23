package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
abstract class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String nome;
	protected String cpf;
	protected String telefone;
	protected Boolean ativo;
	protected Instant dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	protected Instituicao empresa;
	
	protected String endereco;
	protected Byte foto;
	protected Byte biometria;
	
	protected String tipo;
	
	protected List<Acesso> acessos = new ArrayList<>();
	
	public Pessoa() {
	}

	public Pessoa(Long id, String nome, String cpf, String telefone, Boolean ativo, Instant dataCadastro,
			Instituicao empresa, String endereco, Byte foto, Byte biometria) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.ativo = ativo;
		this.dataCadastro = dataCadastro;
		this.empresa = empresa;
		this.endereco = endereco;
		this.foto = foto;
		this.biometria = biometria;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}

	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}

	public Boolean getAtivo() {return ativo;}
	public void setAtivo(Boolean ativo) {this.ativo = ativo;}

	public Instant getDataCadastro() {return dataCadastro;}
	public void setDataCadastro(Instant dataCadastro) {this.dataCadastro = dataCadastro;}

	public Instituicao getEmpresa() {return empresa;}
	public void setEmpresa(Instituicao empresa) {this.empresa = empresa;}

	public String getEndereco() {return endereco;}
	public void setEndereco(String endereco) {this.endereco = endereco;}

	public Byte getFoto() {return foto;}
	public void setFoto(Byte foto) {this.foto = foto;}

	public Byte getBiometria() {return biometria;}
	public void setBiometria(Byte biometria) {this.biometria = biometria;}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}
	
	
	
}
