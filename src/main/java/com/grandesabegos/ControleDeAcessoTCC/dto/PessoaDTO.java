package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

public class PessoaDTO {
    private String tipo; // ex: "ASSINANTE", "FUNCIONARIO", etc.
    private String nome;
    private String cpf;
    private String telefone;
    private Boolean ativo;
    private Instant dataCadastro;
    private Long empresaId; // ID da instituição associada
    private String endereco;
    private byte[] foto;
    private Long biometria;

    public PessoaDTO() {}

    // Getters e Setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getBiometria() {
        return biometria;
    }

    public void setBiometria(Long biometria) {
        this.biometria = biometria;
    }
}
