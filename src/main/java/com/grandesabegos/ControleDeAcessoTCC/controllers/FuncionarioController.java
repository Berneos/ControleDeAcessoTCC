package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;
import com.grandesabegos.ControleDeAcessoTCC.services.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario obj) {
        obj.setId(id);
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /funcionarios/verificar?biometria=123456789&instituicaoId=10
  	@GetMapping("/verificar")
  	public ResponseEntity<String> verificarBiometria(
  	        @RequestParam Long biometria,
  	        @RequestParam Long instituicaoId) {

  		Funcionario funcionario = service.verificarBiometriaPorInstituicao(biometria, instituicaoId);

  	    if (funcionario != null && Boolean.TRUE.equals(funcionario.getAtivo())) {
  	        return ResponseEntity.ok("AUTORIZADO");
  	    } else {
  	        return ResponseEntity.status(403).body("NEGADO");
  	    }
  	}
}
