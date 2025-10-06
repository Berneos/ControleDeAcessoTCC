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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.entities.Responsavel;
import com.grandesabegos.ControleDeAcessoTCC.services.ResponsavelService;

@RestController
@RequestMapping(value = "/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService service;

    @GetMapping
    public ResponseEntity<List<Responsavel>> findAll() {
        List<Responsavel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Responsavel> findById(@PathVariable Long id) {
        Responsavel obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Responsavel> insert(@RequestBody Responsavel obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Responsavel> update(@PathVariable Long id, @RequestBody Responsavel obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @GetMapping("/estudante/{estudanteNome}")
    public ResponseEntity<List<Responsavel>> findByEstudanteNome(@PathVariable String estudanteNome) {
        List<Responsavel> responsaveis = service.findByEstudanteNome(estudanteNome);
        return ResponseEntity.ok().body(responsaveis);
    }
}
