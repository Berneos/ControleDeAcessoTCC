package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandesabegos.ControleDeAcessoTCC.entities.Acesso;
import com.grandesabegos.ControleDeAcessoTCC.services.AcessoService;

@RestController
@RequestMapping(value = "/acessos")
public class AcessoController {

	@Autowired
	private AcessoService service;
	
	@GetMapping
	public ResponseEntity<List<Acesso>> findAll() {
		
		List<Acesso> lista = service.findAll();
	
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Acesso> findById(@PathVariable Long id) {
		
		Acesso obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
}
