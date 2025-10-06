package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.security.UserDetailsImpl;
import com.grandesabegos.ControleDeAcessoTCC.services.InstituicaoService;
import com.grandesabegos.ControleDeAcessoTCC.services.UsuarioService;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    @Autowired
    private InstituicaoService service;

    @Autowired
    private UsuarioService usuarioService; // Para validação de usuário master

    // Listar todas as instituições
    @GetMapping
    public ResponseEntity<List<Instituicao>> findAll() {
        List<Instituicao> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    // Buscar instituição por ID
    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> findById(@PathVariable Long id) {
        Instituicao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // Criar nova instituição (apenas master)
    @PostMapping
    public ResponseEntity<Instituicao> criarInstituicao(@RequestBody Instituicao instituicao,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Apenas master pode criar
        Instituicao criada = usuarioService.criarEmpresa(userDetails.getUsuario(), instituicao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(criada.getId())
                    .toUri();
        return ResponseEntity.created(uri).body(criada);
    }
}
