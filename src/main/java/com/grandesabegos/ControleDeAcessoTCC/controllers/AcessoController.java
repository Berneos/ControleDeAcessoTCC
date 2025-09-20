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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.dto.AcessoFilterDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.AcessoService;

@RestController
@RequestMapping(value = "/acessos")
public class AcessoController {

	@Autowired
	private AcessoService service;
	
	@GetMapping
    public ResponseEntity<Page<AcessoFilterDTO>> findAll(Pageable pageable) {
        Page<AcessoFilterDTO> lista = service.findAllPaged(pageable);
        return ResponseEntity.ok(lista);
    }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Acesso> findById(@PathVariable Long id) {
		
		Acesso obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Acesso> insert(@RequestBody Acesso obj) {
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
	
	// Novo endpoint de filtro
	@GetMapping(value = "/filtrar")
	public ResponseEntity<Page<AcessoFilterDTO>> filtrar(
	        @RequestParam(required = false) Long empresaId,
	        @RequestParam(required = false) Long pessoaId,
	        @RequestParam(required = false) Long usuarioId,
	        @RequestParam(required = false) Long catracaId,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
	        Pageable pageable) {

	    Page<AcessoFilterDTO> page = service.filtrar(empresaId, pessoaId, usuarioId, catracaId, inicio, fim, pageable);
	    return ResponseEntity.ok(page);
	}

	// GET /acessos/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Acesso>> findByEmpresaId(@PathVariable Long empresaId) {
        List<Acesso> acessos = service.findByEmpresaId(empresaId);
        return ResponseEntity.ok().body(acessos);
    }
	
	
}
