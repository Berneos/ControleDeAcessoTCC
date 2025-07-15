package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.services.AssinanteService;
import com.grandesabegos.ControleDeAcessoTCC.services.EstudanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService service;

    @GetMapping
    public ResponseEntity<List<Estudante>> findAll() {
        List<Estudante> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estudante> findById(@PathVariable Long id) {
    	Estudante obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Estudante> insert(@RequestBody Estudante obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estudante> update(@PathVariable Long id, @RequestBody Estudante obj) {
        obj.setId(id); // garantir que o ID correto seja usado
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /estudantes/verificar?biometria=123456789&instituicaoId=10
 	@GetMapping("/verificar")
 	public ResponseEntity<String> verificarBiometria(
 	        @RequestParam Long biometria,
 	        @RequestParam Long instituicaoId) {

 		Estudante estudante = service.verificarBiometriaPorInstituicao(biometria, instituicaoId);

 	    if (estudante != null && Boolean.TRUE.equals(estudante.getAtivo())) {
 	        return ResponseEntity.ok("AUTORIZADO");
 	    } else {
 	        return ResponseEntity.status(403).body("NEGADO");
 	    }
 	}
}
