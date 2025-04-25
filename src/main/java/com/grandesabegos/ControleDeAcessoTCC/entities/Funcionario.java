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
	
}
