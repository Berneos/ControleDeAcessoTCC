package com.grandesabegos.ControleDeAcessoTCC.entities;



import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_acessos")
public class Acesso implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Instituicao empresa;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "catraca_id")
	private Catraca catraca;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataAcesso;

	public Acesso() {}
	
	public Acesso(Long id, Instituicao empresa, Pessoa pessoa, Usuario usuario, Catraca catraca, Instant dataAcesso) {
		this.id = id;
		this.empresa = empresa;
		this.pessoa = pessoa;
		this.usuario = usuario;
		this.catraca = catraca;
		this.dataAcesso = dataAcesso;
	}

	public Instituicao getEmpresa() {return empresa;}
	public void setEmpresa(Instituicao empresa) {this.empresa = empresa;}

	public Pessoa getPessoa() {return pessoa;}
	public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}

	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}

	public Catraca getCatraca() {return catraca;}
	public void setCatraca(Catraca catraca) {this.catraca = catraca;}

	public Instant getDataAcesso() {return dataAcesso;}
	public void setDataAcesso(Instant dataAcesso) {this.dataAcesso = dataAcesso;}

	public Long getId() {
		return id;
	}
	
	
	
}
