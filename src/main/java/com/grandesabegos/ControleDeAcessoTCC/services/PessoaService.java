package com.grandesabegos.ControleDeAcessoTCC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.repositories.CatracaRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PessoaRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.DatabaseException;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public abstract class PessoaService<T extends Pessoa> {

    @Autowired
    protected PessoaRepository<T> repository;
    
    @Autowired
    private InstituicaoRepository instituicaoRepository;
    
    @Autowired
    private CatracaRepository catracaRepository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        Optional<T> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public T insert(T obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            T obj = findById(id);
            repository.delete(obj);
        } catch (Exception e) {
            if (e instanceof org.springframework.dao.EmptyResultDataAccessException) {
                throw new ResourceNotFoundException(id);
            }
            if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
                throw new DatabaseException(e.getMessage());
            }
            throw e;
        }
    }

    public T update(Long id, T obj) {
        try {
            T entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(T entity, T obj) {
        entity.setNome(obj.getNome());
        entity.setAtivo(obj.getAtivo());
        entity.setBiometria(obj.getBiometria());
        entity.setCpf(obj.getCpf());
        entity.setEndereco(obj.getEndereco());
        entity.setFoto(obj.getFoto());
        entity.setTelefone(obj.getTelefone());
    }

    public T verificarBiometriaPorInstituicao(String biometria, Long instituicaoId) {
    	
    	Instituicao inst = instituicaoRepository.findById(instituicaoId)
    		    .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada"));

        return repository.findByBiometriaAndEmpresaId(biometria, instituicaoId)
            .orElse(null);
    }

}
