package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@DiscriminatorValue("F")
public class Funcionario extends Pessoa {

	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	private Double salario;

	
	public Funcionario( Setor setor, Cargo cargo, Double salario) {
		super();
		this.setor = setor;
		this.cargo = cargo;
		this.salario = salario;
	}
	
	public Funcionario(Long id, String nome, String cpf, String telefone, Boolean ativo,
            Instant dataCadastro, Instituicao empresa, String endereco, byte[] foto, Long biometria) {
		super(id, nome, cpf, telefone, ativo, dataCadastro, empresa, endereco, foto, biometria);
		}


	public Setor getSetor() {return setor;}
	public void setSetor(Setor setor) {this.setor = setor;}

	public Cargo getCargo() {return cargo;}
	public void setCargo(Cargo cargo) {	this.cargo = cargo;}

	public Double getSalario() {return salario;}
	public void setSalario(Double salario) {this.salario = salario;}
	
}
