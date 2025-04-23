package com.grandesabegos.ControleDeAcessoTCC.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("E")
public class Estudante extends Pessoa{

	

	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "estudante", cascade = CascadeType.ALL)
	private Responsavel responsavel;

	public Estudante(Responsavel responsavel) {
		super();
		this.responsavel = responsavel;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
}
