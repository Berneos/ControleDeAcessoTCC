package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Biometria;
import com.grandesabegos.ControleDeAcessoTCC.services.BiometriaService;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    @Autowired
    private BiometriaService service;

    @GetMapping
    public ResponseEntity<List<Biometria>> findAll() {
        List<Biometria> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biometria> findById(@PathVariable Long id) {
        Biometria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Biometria> insert(@RequestBody Biometria obj) {
        Biometria created = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biometria> update(@PathVariable Long id, @RequestBody Biometria obj) {
        Biometria updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    @GetMapping("/ultima")
    public ResponseEntity<Biometria> getMostRecent() {
        Biometria ultima = service.getMostRecent();
        if (ultima == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ultima);
    }
}
