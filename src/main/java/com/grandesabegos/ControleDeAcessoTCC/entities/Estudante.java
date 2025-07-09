package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.time.Instant;

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
	
	public Estudante(Long id, String nome, String cpf, String telefone, Boolean ativo,
            Instant dataCadastro, Instituicao empresa, String endereco, byte[] foto, Long biometria) {
		super(id, nome, cpf, telefone, ativo, dataCadastro, empresa, endereco, foto, biometria);
		}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
}
