package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;

public class EstudanteFilterDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Boolean ativo;
    private LocalDate dataCadastro;   // agora LocalDate
    private String responsavelNome;
    private Long empresaId;
    private LocalDate dataInicio;     // filtro: início (yyyy-MM-dd)
    private LocalDate dataFim;        // filtro: fim (yyyy-MM-dd)

    public EstudanteFilterDTO() {}

    // Construtor a partir da entidade (converte Instant -> LocalDate em UTC)
    public EstudanteFilterDTO(Estudante entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.ativo = entity.getAtivo();
        Instant inst = entity.getDataCadastro();
        this.dataCadastro = inst != null ? Instant.ofEpochMilli(inst.toEpochMilli()).atZone(ZoneOffset.UTC).toLocalDate() : null;
        this.responsavelNome = entity.getResponsavel() != null ? entity.getResponsavel().getNome() : null;
        this.empresaId = entity.getEmpresa() != null ? entity.getEmpresa().getId() : null;
    }

    public EstudanteFilterDTO(Long id, String nome, String cpf, Boolean ativo, LocalDate dataCadastro,
                              String responsavelNome, Long empresaId, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.responsavelNome = responsavelNome;
        this.empresaId = empresaId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getResponsavelNome() { return responsavelNome; }
    public void setResponsavelNome(String responsavelNome) { this.responsavelNome = responsavelNome; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
