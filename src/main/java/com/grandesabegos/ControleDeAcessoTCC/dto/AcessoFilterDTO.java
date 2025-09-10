package com.grandesabegos.ControleDeAcessoTCC.dto;

import java.time.Instant;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;

public class AcessoFilterDTO {

    private Long id;
    private String pessoaNome;
    private String usuarioNome;
    private String catracaNome;
    private Instant dataAcesso;

    public AcessoFilterDTO(Acesso entity) {
        this.id = entity.getId();
        this.pessoaNome = entity.getPessoa() != null ? entity.getPessoa().getNome() : null;
        this.usuarioNome = entity.getUsuario() != null ? entity.getUsuario().getNome() : null;
        this.catracaNome = entity.getCatraca() != null ? entity.getCatraca().getNome() : null;
        this.dataAcesso = entity.getDataAcesso();
    }

    public Long getId() { return id; }
    public String getPessoaNome() { return pessoaNome; }
    public String getUsuarioNome() { return usuarioNome; }
    public String getCatracaNome() { return catracaNome; }
    public Instant getDataAcesso() { return dataAcesso; }
}
