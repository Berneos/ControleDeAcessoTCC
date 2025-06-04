package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "tb_plano")
public class Plano implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "plano")
	private Set<Assinante> assinantes = new HashSet<>(); 
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Instituicao empresa;
	
	public Plano(Long id, String nome, Double preco, String descricao, Set<Assinante> assinantes,
			Instituicao empresa) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.assinantes = assinantes;
		this.empresa = empresa;
	}
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}
	
	public String getDescricao() {	return descricao;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
	public Set<Assinante> getAssinantes() {return assinantes;}
	public void setAssinantes(Set<Assinante> assinantes) {this.assinantes = assinantes;}
	
	public Instituicao getEmpresa() {return empresa;}
	public void setEmpresa(Instituicao empresa) {this.empresa = empresa;}
	
	
}
