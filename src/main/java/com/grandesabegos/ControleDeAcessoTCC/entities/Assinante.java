package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Assinante extends Pessoa {

	
	private static final long serialVersionUID = 1L;

	
	private Plano plano;
	private Instant dataVencimento;
	
	public Assinante(Plano plano, Instant dataVencimento) {
		super();
		this.plano = plano;
		this.dataVencimento = dataVencimento;
	}

	public Plano getPlano() {return plano;}
	public void setPlano(Plano plano) {this.plano = plano;}

	public Instant getDataVencimento() {return dataVencimento;}
	public void setDataVencimento(Instant dataVencimento) {this.dataVencimento = dataVencimento;}
	
	
}
