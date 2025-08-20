package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.AssinanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
}
