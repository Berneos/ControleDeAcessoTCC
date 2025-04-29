package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class Funcionario extends Pessoa {

	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Setor setor;
	private Cargo cargo;
	private Double salario;
	
	public Funcionario(Long id, Setor setor, Cargo cargo, Double salario) {
		super();
		this.id = id;
		this.setor = setor;
		this.cargo = cargo;
		this.salario = salario;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Setor getSetor() {return setor;}
	public void setSetor(Setor setor) {this.setor = setor;}

	public Cargo getCargo() {return cargo;}
	public void setCargo(Cargo cargo) {	this.cargo = cargo;}

	public Double getSalario() {	return salario;}
	public void setSalario(Double salario) {this.salario = salario;}
	
}
