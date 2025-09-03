package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.grandesabegos.ControleDeAcessoTCC.dto.EstudanteFilterDTO;
import com.grandesabegos.ControleDeAcessoTCC.dto.EstudanteMinDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Estudante;
import com.grandesabegos.ControleDeAcessoTCC.services.EstudanteService;

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
 	        @RequestParam String biometria,
 	        @RequestParam Long instituicaoId) {

 		Estudante estudante = service.verificarBiometriaPorInstituicao(biometria, instituicaoId);

 	    if (estudante != null && Boolean.TRUE.equals(estudante.getAtivo())) {
 	        return ResponseEntity.ok("AUTORIZADO");
 	    } else {
 	        return ResponseEntity.status(403).body("NEGADO");
 	    }
 	}
 	
 	@GetMapping("/filtrar")
 	public ResponseEntity<Page<EstudanteMinDTO>> filtrar(
 	        @RequestParam(required = false) String nome,
 	        @RequestParam(required = false) String cpf,
 	        @RequestParam(required = false) Boolean ativo,
 	        @RequestParam(required = false) Long empresaId,
 	        @RequestParam(required = false) String responsavelNome,
 	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
 	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
 	        Pageable pageable) {

 	    EstudanteFilterDTO filtro = new EstudanteFilterDTO();
 	    filtro.setNome(nome);
 	    filtro.setCpf(cpf);
 	    filtro.setAtivo(ativo);
 	    filtro.setEmpresaId(empresaId);
 	    filtro.setResponsavelNome(responsavelNome);
 	    filtro.setDataInicio(dataInicio);
 	    filtro.setDataFim(dataFim);

 	    Page<EstudanteMinDTO> page = service.filtrarEstudantes(filtro, pageable);
 	    return ResponseEntity.ok(page);
 	}


}
