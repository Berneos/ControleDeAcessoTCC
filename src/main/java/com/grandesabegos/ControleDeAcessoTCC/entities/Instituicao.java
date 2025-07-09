package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_instituicao")
public class Instituicao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataCadastro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private Set<Pessoa> pessoas = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private Set<Usuario> usuarios = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private Set<Catraca> catracas = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private Set<Acesso> acessos = new HashSet<>();	
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private Set<Plano> planos = new HashSet<>();	
	
	private Integer tipo;
	
	public Instituicao() {}
	
	public Instituicao(Long id, String nome, String cnpj, Instant dataCadastro, Set<Pessoa> pessoas,
			Set<Usuario> usuarios, Set<Catraca> catracas, Set<Acesso> acessos, Set<Plano> planos, Tipo tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.pessoas = pessoas;
		this.usuarios = usuarios;
		this.catracas = catracas;
		this.acessos = acessos;
		this.planos = planos;
		setTipo(tipo);
	}
	public Instituicao(Long id, String nome, String cnpj, Instant dataCadastro, Set<Pessoa> pessoas,
			Set<Usuario> usuarios, Set<Catraca> catracas, Set<Acesso> acessos, Tipo tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.pessoas = pessoas;
		this.usuarios = usuarios;
		this.catracas = catracas;
		this.acessos = acessos;
		setTipo(tipo);
	}


	public Long getId() {return id;}
	public void setId(Long id) {
	    this.id = id;
	}


	public String getNome() {return nome;}
	public void ListNome(String nome) {this.nome = nome;}

	public String getCnpj() {return cnpj;}
	public void ListCnpj(String cnpj) {this.cnpj = cnpj;}

	public Instant getDataCadastro() {return dataCadastro;}
	public void ListDataCadastro(Instant dataCadastro) {this.dataCadastro = dataCadastro;}

	public Set<Pessoa> getPessoas() {return pessoas;}

	public Set<Usuario> getUsuarios() {return usuarios;}

	public Set<Catraca> getCatracas() {return catracas;}

	public Set<Acesso> getAcessos() {return acessos;}

	public Optional<Set<Plano>> getPlanos() {return Optional.ofNullable(planos);}

	public Tipo getTipo() {return Tipo.valueOf(tipo);}
	public void setTipo(Tipo tipo) {
	
		if(tipo != null) {
			this.tipo = tipo.getCode();
		}
		
		
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instituicao other = (Instituicao) obj;
		return Objects.equals(cnpj, other.cnpj);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

}
