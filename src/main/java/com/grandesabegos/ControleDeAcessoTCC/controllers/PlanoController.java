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
import com.grandesabegos.ControleDeAcessoTCC.entities.Plano;
import com.grandesabegos.ControleDeAcessoTCC.services.PlanoService;

@RestController
@RequestMapping(value = "/planos")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @GetMapping
    public ResponseEntity<List<Plano>> findAll() {
        List<Plano> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Plano> findById(@PathVariable Long id) {
        Plano obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Plano> insert(@RequestBody Plano obj) {
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
    public ResponseEntity<Plano> update(@PathVariable Long id, @RequestBody Plano obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
 // GET /planos/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Plano>> findByEmpresaId(@PathVariable Long empresaId) {
        List<Plano> planos = service.findByEmpresaId(empresaId);
        return ResponseEntity.ok().body(planos);
    }
}
