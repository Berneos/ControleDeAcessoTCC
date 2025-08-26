package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.time.Instant;
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

import com.grandesabegos.ControleDeAcessoTCC.dto.AssinanteMinDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.services.AssinanteService;

@RestController
@RequestMapping(value = "/assinantes")
public class AssinanteController {

	@Autowired
	private AssinanteService service;

	@GetMapping
	public ResponseEntity<List<Assinante>> findAll() {
		List<Assinante> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Assinante> findById(@PathVariable Long id) {
		Assinante obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Assinante> insert(@RequestBody Assinante obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Assinante> update(@PathVariable Long id, @RequestBody Assinante obj) {
		obj.setId(id); // garantir que o ID correto seja usado
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// GET /assinantes/verificar?biometria=123456789&instituicaoId=10
	@GetMapping("/verificar")
	public ResponseEntity<String> verificarBiometria(@RequestParam String biometria, @RequestParam Long instituicaoId) {

		Assinante assinante = service.verificarBiometriaPorInstituicao(biometria, instituicaoId);

		if (assinante != null && Boolean.TRUE.equals(assinante.getAtivo())) {
			return ResponseEntity.ok("AUTORIZADO");
		} else {
			return ResponseEntity.status(403).body("NEGADO");
		}
	}
	
	@GetMapping("/filtrar")
    public Page<AssinanteMinDTO> filtrar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant fim,
            @RequestParam(required = false) String nomePlano,
            Pageable pageable) {
        return service.filtrar(nome, ativo, inicio, fim, nomePlano, pageable);
    }
}
