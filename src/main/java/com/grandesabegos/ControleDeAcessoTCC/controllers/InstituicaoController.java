package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.services.InstituicaoService;

@RestController
@RequestMapping(value = "/instituicoes")
public class InstituicaoController {

	@Autowired
	private InstituicaoService service;
	
	@GetMapping
	public ResponseEntity<List<Instituicao>> findAll() {
		
		List<Instituicao> lista = service.findAll();
	
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Instituicao> findById(@PathVariable Long id) {
		
		Instituicao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
}
