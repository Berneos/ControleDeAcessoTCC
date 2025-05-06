package com.grandesabegos.ControleDeAcessoTCC.entities;

import java.time.Instant;

import org.hibernate.type.TrueFalseConverter;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("A")
public class Assinante extends Pessoa {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "plano_id")
	private Plano plano;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataVencimento;
	
	@Convert(converter = TrueFalseConverter.class)
	private Boolean ativo;

	public Assinante(Plano plano, Instant dataVencimento, Boolean ativo) {
		super();
		this.plano = plano;
		this.dataVencimento = dataVencimento;
		this.ativo = ativo;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Instant getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Instant dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
