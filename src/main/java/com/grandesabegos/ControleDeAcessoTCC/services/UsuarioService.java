package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.UsuarioRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario insert(Usuario obj) {
        obj.setSenha(passwordEncoder.encode(obj.getSenha()));
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            Usuario obj = findById(id);
            repository.delete(obj);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Usuario update(Long id, Usuario obj) {
        try {
            Usuario entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Usuario entity, Usuario obj) {
        entity.setNome(obj.getNome());
        entity.setCpf(obj.getCpf());
        entity.setEmail(obj.getEmail());
        entity.setIsAdmin(obj.getIsAdmin());
        entity.setTelefone(obj.getTelefone());
        entity.setUsername(obj.getUsername());

        if (obj.getSenha() != null && !obj.getSenha().isBlank()) {
            entity.setSenha(passwordEncoder.encode(obj.getSenha()));
        }
    }
    public List<Usuario> findByEmpresaId(Long empresaId) {
        return repository.findByEmpresaId(empresaId);
    }
    
 // --- Criar empresa ---
    public Instituicao criarEmpresa(Usuario usuarioLogado, Instituicao novaEmpresa) {
        if (!Boolean.TRUE.equals(usuarioLogado.getIsMaster())) {
            throw new SecurityException("Apenas usuário master pode criar empresas.");
        }
        return instituicaoRepository.save(novaEmpresa);
    }

    // --- Criar usuário ---
    public Usuario criarUsuario(Usuario usuarioLogado, Usuario novoUsuario, Long empresaId) {
        Instituicao empresa = instituicaoRepository.findById(empresaId)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada: " + empresaId));

        if (Boolean.TRUE.equals(usuarioLogado.getIsMaster())) {
            // Master pode criar usuário em qualquer empresa
            novoUsuario.setEmpresa(empresa);
        } else if (Boolean.TRUE.equals(usuarioLogado.getIsAdmin())) {
            // Admin só pode criar usuários na própria empresa
            if (!usuarioLogado.getEmpresa().getId().equals(empresaId)) {
                throw new SecurityException("Admin só pode criar usuários para a própria empresa.");
            }
            novoUsuario.setEmpresa(usuarioLogado.getEmpresa());
        } else {
            throw new SecurityException("Usuário não tem permissão para criar novos usuários.");
        }

        // Criptografa senha
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        return repository.save(novoUsuario);
    }

}
