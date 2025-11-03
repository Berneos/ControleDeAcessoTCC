package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;

public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String nomeEmpresa;
    private String cnpj;
    private Tipo tipo;
    private Boolean ativo;
    private Instant dataCadastro;

    public PessoaResponseDTO() {}

    public PessoaResponseDTO(Pessoa entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.telefone = entity.getTelefone();
        this.ativo = entity.getAtivo();
        this.dataCadastro = entity.getDataCadastro();

        if (entity.getEmpresa() != null) {
            this.nomeEmpresa = entity.getEmpresa().getNome();
            this.cnpj = entity.getEmpresa().getCnpj();
            this.tipo = entity.getEmpresa().getTipo();
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Instant getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Instant dataCadastro) { this.dataCadastro = dataCadastro; }
}
