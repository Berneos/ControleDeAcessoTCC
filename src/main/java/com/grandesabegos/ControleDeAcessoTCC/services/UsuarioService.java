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

    // --- Atualizado para aceitar empresaId e validar permissões ---
    public Usuario update(Usuario usuarioLogado, Long id, Usuario obj, Long empresaId) {
        try {
            Usuario entity = repository.getReferenceById(id);

            // Validação de permissão por empresa
            if (!Boolean.TRUE.equals(usuarioLogado.getIsMaster())) {
                if (Boolean.TRUE.equals(usuarioLogado.getIsAdmin())) {
                    // Admin só pode atualizar usuários da própria empresa
                    if (!usuarioLogado.getEmpresa().getId().equals(empresaId)) {
                        throw new SecurityException("Admin só pode atualizar usuários da própria empresa.");
                    }
                    entity.setEmpresa(usuarioLogado.getEmpresa());
                } else {
                    throw new SecurityException("Usuário não tem permissão para atualizar usuários.");
                }
            } else if (empresaId != null) {
                // Master pode mudar a empresa do usuário
                Instituicao empresa = instituicaoRepository.findById(empresaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada: " + empresaId));
                entity.setEmpresa(empresa);
            }

            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // --- Atualizado para aceitar empresaId e validar permissões ---
    public void delete(Usuario usuarioLogado, Long id, Long empresaId) {
        try {
            Usuario obj = findById(id);

            if (!Boolean.TRUE.equals(usuarioLogado.getIsMaster())) {
                if (Boolean.TRUE.equals(usuarioLogado.getIsAdmin())) {
                    if (!usuarioLogado.getEmpresa().getId().equals(empresaId)) {
                        throw new SecurityException("Admin só pode deletar usuários da própria empresa.");
                    }
                } else {
                    throw new SecurityException("Usuário não tem permissão para deletar usuários.");
                }
            }

            repository.delete(obj);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
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
            novoUsuario.setEmpresa(empresa);
        } else if (Boolean.TRUE.equals(usuarioLogado.getIsAdmin())) {
            if (!usuarioLogado.getEmpresa().getId().equals(empresaId)) {
                throw new SecurityException("Admin só pode criar usuários para a própria empresa.");
            }
            novoUsuario.setEmpresa(usuarioLogado.getEmpresa());
        } else {
            throw new SecurityException("Usuário não tem permissão para criar novos usuários.");
        }

        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        return repository.save(novoUsuario);
    }
}
