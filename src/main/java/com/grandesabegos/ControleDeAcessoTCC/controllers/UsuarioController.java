package com.grandesabegos.ControleDeAcessoTCC.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.grandesabegos.ControleDeAcessoTCC.dto.UsuarioCreateDTO;
import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;
import com.grandesabegos.ControleDeAcessoTCC.security.UserDetailsImpl;
import com.grandesabegos.ControleDeAcessoTCC.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(
            @RequestBody @Valid UsuarioCreateDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Usuario autenticado = userDetails.getUsuario();

        // Permissões básicas
        if (!Boolean.TRUE.equals(autenticado.getIsMaster()) && !Boolean.TRUE.equals(autenticado.getIsAdmin())) {
            return ResponseEntity.status(403).body("Usuário não tem permissão para criar novos usuários.");
        }

        // Validação senha — o @Valid já cobre, mas checamos mesmo assim
        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            return ResponseEntity.badRequest().body("A senha é obrigatória para criar um novo usuário.");
        }

        // Se Admin (não Master), força empresaId para a empresa do autenticado
        Long empresaId = dto.getEmpresaId();
        if (!Boolean.TRUE.equals(autenticado.getIsMaster()) && Boolean.TRUE.equals(autenticado.getIsAdmin())) {
            empresaId = autenticado.getEmpresa().getId();
        }

        // Monta entidade Usuario a partir do DTO (sem associar empresa)
        Usuario novo = new Usuario();
        novo.setNome(dto.getNome());
        novo.setCpf(dto.getCpf());
        novo.setTelefone(dto.getTelefone());
        novo.setIsAdmin(dto.getIsAdmin());   // supondo que seu entity tem esses campos booleanos
        novo.setIsMaster(dto.getIsMaster());
        novo.setUsername(dto.getUsername());
        novo.setEmail(dto.getEmail());
        novo.setSenha(dto.getSenha()); // service vai codificar

        // chama service (seu service já tinha: criarUsuario(autenticado, usuario, empresaId))
        Usuario criado = service.criarUsuario(autenticado, novo, empresaId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.getId())
                .toUri();

        return ResponseEntity.created(uri).body(criado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Usuario obj,
                                    @RequestParam(required = false) Long empresaId,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Usuario autenticado = userDetails.getUsuario();

        // 🔒 Verifica se o usuário é ADMIN ou MASTER
        if (!Boolean.TRUE.equals(autenticado.getIsAdmin()) && !Boolean.TRUE.equals(autenticado.getIsMaster())) {
            return ResponseEntity.status(403).body("Você não tem permissão para alterar usuários.");
        }

        Usuario atualizado = service.update(autenticado, id, obj, empresaId);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestParam(required = false) Long empresaId,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Usuario autenticado = userDetails.getUsuario();
        service.delete(autenticado, id, empresaId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Usuario>> findByEmpresaId(@PathVariable Long empresaId) {
        return ResponseEntity.ok(service.findByEmpresaId(empresaId));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

}
