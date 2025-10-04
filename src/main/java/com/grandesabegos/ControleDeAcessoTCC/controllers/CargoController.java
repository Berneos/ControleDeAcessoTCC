package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.grandesabegos.ControleDeAcessoTCC.entities.Cargo;
import com.grandesabegos.ControleDeAcessoTCC.services.CargoService;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    // GET /cargos
    @GetMapping
    public ResponseEntity<List<Cargo>> findAll() {
        List<Cargo> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    // GET /cargos/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cargo> findById(@PathVariable Long id) {
        Cargo obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // POST /cargos
    @PostMapping
    public ResponseEntity<Cargo> insert(@RequestBody Cargo obj) {
        Cargo created = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    // PUT /cargos/{id}
    @PutMapping(value = "/{id}")
    public ResponseEntity<Cargo> update(@PathVariable Long id, @RequestBody Cargo obj) {
        Cargo updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    // DELETE /cargos/{id}
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
 // GET /cargos/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Cargo>> findByEmpresaId(@PathVariable Long empresaId) {
        List<Cargo> cargos = service.findByEmpresaId(empresaId);
        return ResponseEntity.ok().body(cargos);
    }
    
    @GetMapping("/filtrar")
    public ResponseEntity<Page<Cargo>> filtrar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long setorId,
            Pageable pageable) {

        Page<Cargo> resultado = service.filtrar(nome, setorId, pageable);
        return ResponseEntity.ok(resultado);
    }

}
