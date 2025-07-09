package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import com.grandesabegos.ControleDeAcessoTCC.dto.PessoaDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Assinante;
import com.grandesabegos.ControleDeAcessoTCC.entities.Funcionario;
import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.services.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody PessoaDTO dto) {
		Pessoa obj = mapDtoToEntity(dto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody PessoaDTO dto) {
		Pessoa obj = mapDtoToEntity(dto);
		obj.setId(id); // garantir que o ID correto seja usado
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/verificar")
	public ResponseEntity<?> verificarBiometria(@RequestParam Long biometria) {
		Pessoa pessoa = service.verificarBiometria(biometria);

		if (pessoa != null && Boolean.TRUE.equals(pessoa.getAtivo())) {
			return ResponseEntity.ok("AUTORIZADO");
		} else {
			return ResponseEntity.status(403).body("NEGADO");
		}
	}

	// Método auxiliar para mapear o DTO para a entidade correta
	private Pessoa mapDtoToEntity(PessoaDTO dto) {
		Instituicao empresa = new Instituicao();
		empresa.setId(dto.getEmpresaId());

		switch (dto.getTipo().toUpperCase()) {
		case "ASSINANTE":
			return new Assinante(null, dto.getNome(), dto.getCpf(), dto.getTelefone(), dto.getAtivo(),
					dto.getDataCadastro(), empresa, dto.getEndereco(), dto.getFoto(), dto.getBiometria());
		case "FUNCIONARIO":
			return new Funcionario(null, dto.getNome(), dto.getCpf(), dto.getTelefone(), dto.getAtivo(),
					dto.getDataCadastro(), empresa, dto.getEndereco(), dto.getFoto(), dto.getBiometria());
		// Adicione outros tipos conforme necessário
		default:
			throw new IllegalArgumentException("Tipo de pessoa inválido: " + dto.getTipo());
		}
	}
}
