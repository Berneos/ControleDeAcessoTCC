package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.PessoaPadraoService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaPadraoService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@RequestBody Pessoa obj) {
        Pessoa created = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa obj) {
        obj.setId(id); // garantir que o ID correto seja usado
        Pessoa updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /pessoas/verificar?biometria=123456789&instituicaoId=10
    @GetMapping("/verificar")
    public ResponseEntity<String> verificarBiometria(
            @RequestParam Long biometria,
            @RequestParam Long instituicaoId) {

        Pessoa pessoa = service.verificarBiometriaPorInstituicao(biometria, instituicaoId);

        if (pessoa != null && Boolean.TRUE.equals(pessoa.getAtivo())) {
            return ResponseEntity.ok("AUTORIZADO");
        } else {
            return ResponseEntity.status(403).body("NEGADO");
        }
    }
}
