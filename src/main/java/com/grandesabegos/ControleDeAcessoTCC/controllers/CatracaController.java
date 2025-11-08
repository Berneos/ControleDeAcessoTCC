package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

import com.grandesabegos.ControleDeAcessoTCC.dto.CatracaCreateDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Catraca;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.services.CatracaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/catracas")
public class CatracaController {

    @Autowired
    private CatracaService service;

    @GetMapping
    public ResponseEntity<List<Catraca>> findAll() {
        List<Catraca> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Catraca> findById(@PathVariable Long id) {
        Catraca obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody CatracaCreateDTO dto) {
        if (dto.getEmpresaId() == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "empresaId é obrigatório."));
        }

        Catraca catraca = new Catraca();
        catraca.setNome(dto.getNome());

        Instituicao empresa = new Instituicao();
        empresa.setId(dto.getEmpresaId());
        catraca.setEmpresa(empresa);

        Catraca created = service.insert(catraca);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new CatracaCreateDTO(created));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Catraca> update(@PathVariable Long id, @RequestBody Catraca obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    
 // GET /catracas/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Catraca>> findByEmpresaId(@PathVariable Long empresaId) {
        List<Catraca> catracas = service.findByEmpresaId(empresaId);
        return ResponseEntity.ok().body(catracas);
    }
    
    @GetMapping("/filtrar")
    public ResponseEntity<List<Catraca>> filtrarPorNome(@org.springframework.web.bind.annotation.RequestParam String nome) {
        List<Catraca> catracas = service.findByNome(nome);
        return ResponseEntity.ok().body(catracas);
    }

}
