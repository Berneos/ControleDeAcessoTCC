package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.dto.PessoaCreateDTO;
import com.grandesabegos.ControleDeAcessoTCC.dto.PessoaResponseDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.PessoaPadraoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaPadraoService service;

    // ... outros endpoints (GET, DELETE etc) permanecem iguais

    /**
     * Criar pessoa a partir do DTO (recebe apenas empresaId).
     */
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody PessoaCreateDTO dto) {
        if (dto.getEmpresaId() == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "empresaId é obrigatório."));
        }

        Pessoa created;
        try {
            created = service.insertFromDto(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro interno: " + e.getMessage()));
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();

        return ResponseEntity.created(uri).body(new PessoaResponseDTO(created));
    }

    /**
     * Atualizar pessoa: recebe DTO e aplica alterações ao objeto existente.
     */
    @PutMapping(value = "/id/{id}")
    public ResponseEntity<PessoaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaCreateDTO dto) {
        dto.setId(id);

        Pessoa existing = service.findById(id);
        mergeDtoIntoEntity(dto, existing);
        Pessoa updated = service.update(id, existing);

        return ResponseEntity.ok().body(new PessoaResponseDTO(updated));
    }

    /**
     * Listar pessoas de uma empresa, retornando apenas os campos necessários.
     */
    @GetMapping(value = "/empresa/{empresaId}")
    public ResponseEntity<List<PessoaResponseDTO>> findByEmpresa(@PathVariable Long empresaId) {
        List<Pessoa> list = service.findByEmpresaId(empresaId);
        List<PessoaResponseDTO> dtoList = list.stream()
            .map(PessoaResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    // ---- Helpers de mapeamento ----
    private Pessoa fromDto(PessoaCreateDTO dto) {
        Pessoa p = new Pessoa();
        p.setNome(dto.getNome());
        p.setCpf(dto.getCpf());
        p.setTelefone(dto.getTelefone());
        p.setBiometria(dto.getBiometria());
        p.setAtivo(dto.getAtivo() == null ? Boolean.TRUE : dto.getAtivo());
        p.setDataCadastro(dto.getDataCadastro() != null ? dto.getDataCadastro() : Instant.now());

        // liga empresa apenas pelo id — evita fazer consulta extra aqui
        if (dto.getEmpresaId() != null) {
            Instituicao e = new Instituicao();
            e.setId(dto.getEmpresaId());
            p.setEmpresa(e);
        }

        return p;
    }

    private void mergeDtoIntoEntity(PessoaCreateDTO dto, Pessoa entity) {
        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getCpf() != null) entity.setCpf(dto.getCpf());
        if (dto.getTelefone() != null) entity.setTelefone(dto.getTelefone());
        if (dto.getBiometria() != null) entity.setBiometria(dto.getBiometria());
        if (dto.getAtivo() != null) entity.setAtivo(dto.getAtivo());
        if (dto.getDataCadastro() != null) entity.setDataCadastro(dto.getDataCadastro());

        if (dto.getEmpresaId() != null) {
        	Instituicao e = new Instituicao();
            e.setId(dto.getEmpresaId());
            entity.setEmpresa(e);
        }
    }

    // ... demais endpoints do controller (findAll, findById, filtrar ...)
}
