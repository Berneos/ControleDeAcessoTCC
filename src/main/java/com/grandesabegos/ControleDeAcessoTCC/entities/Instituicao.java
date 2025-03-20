package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private Instant dataCadastro;
	
	@OneToMany(mappedBy = "empresa")
	private List<Pessoa> pessoas = new ArrayList<>();
	
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Catraca> catracas = new ArrayList<>();
	private List<Acesso> acessos = new ArrayList<>();	
	private List<Plano> planos = new ArrayList<>();	
	private Tipo tipo;
	
	
	public Instituicao(Long id, String nome, String cnpj, Instant dataCadastro, List<Pessoa> pessoas,
			List<Usuario> usuarios, List<Catraca> catracas, List<Acesso> acessos, List<Plano> planos, Tipo tipo) {
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
		this.tipo = tipo;
	}
	public Instituicao(Long id, String nome, String cnpj, Instant dataCadastro, List<Pessoa> pessoas,
			List<Usuario> usuarios, List<Catraca> catracas, List<Acesso> acessos, Tipo tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.pessoas = pessoas;
		this.usuarios = usuarios;
		this.catracas = catracas;
		this.acessos = acessos;
		this.tipo = tipo;
	}


	public Long getId() {return id;}
	public void ListId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void ListNome(String nome) {this.nome = nome;}

	public String getCnpj() {return cnpj;}
	public void ListCnpj(String cnpj) {this.cnpj = cnpj;}

	public Instant getDataCadastro() {return dataCadastro;}
	public void ListDataCadastro(Instant dataCadastro) {this.dataCadastro = dataCadastro;}

	public List<Pessoa> getPessoas() {return pessoas;}

	public List<Usuario> getUsuarios() {return usuarios;}

	public List<Catraca> getCatracas() {return catracas;}

	public List<Acesso> getAcessos() {return acessos;}

	public Optional<List<Plano>> getPlanos() {return Optional.ofNullable(planos);}

	public Tipo getTipo() {return tipo;}
	public void ListTipo(Tipo tipo) {this.tipo = tipo;}


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
	
	

}
