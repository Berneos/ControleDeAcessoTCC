package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_setores")
public class Setor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor")
	private Set<Funcionario> funcionario = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor")
	private Set<Cargo> cargos = new HashSet<>();
	
	
	
	public Setor(Long id, String nome, List<Funcionario> funcionario, List<Cargo> cargos) {

		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public Set<Funcionario> getFuncionario() {return funcionario;}

	public Set<Cargo> getCargos() {return cargos;}


	@Override
	public int hashCode() {
		return Objects.hash(cargos, funcionario, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		return Objects.equals(cargos, other.cargos) && Objects.equals(funcionario, other.funcionario)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	
}
