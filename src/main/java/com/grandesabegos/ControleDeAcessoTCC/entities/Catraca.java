package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.util.HashSet;
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
@Table(name = "tb_catraca")
public class Catraca implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Instituicao empresa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "catraca")
	private Set<Acesso> acessos = new HashSet<>();

	
	public Catraca(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Catraca() {}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public Instituicao getEmpresa() {return empresa;}
	public void setEmpresa(Instituicao empresa) {this.empresa = empresa;}
	
}
