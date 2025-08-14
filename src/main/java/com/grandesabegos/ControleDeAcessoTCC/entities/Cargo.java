package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cargos")
public class Cargo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cargo")
	private Set<Funcionario> funcionarios = new HashSet<>();



	
	public Cargo(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Cargo() {}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}


	public Set<Funcionario> getFuncionarios() {return funcionarios;}


	public Setor getSetor() {return setor;}



	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		return Objects.equals(nome, other.nome);
	}
	
}
