package com.grandesabegos.ControleDeAcessoTCC.dto;

import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;

public class CatracaCreateDTO {

    private Long id;
    private String nome;
    private Long empresaId; // apenas o ID da empresa

    public CatracaCreateDTO() {}

    public CatracaCreateDTO(Catraca entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        if (entity.getEmpresa() != null) {
            this.empresaId = entity.getEmpresa().getId();
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}
