package com.grandesabegos.ControleDeAcessoTCC.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Biometria;
import com.grandesabegos.ControleDeAcessoTCC.repositories.BiometriaRepository;
import com.grandesabegos.ControleDeAcessoTCC.services.exceptions.ResourceNotFoundException;

@Service
public class BiometriaService {

    @Autowired
    private BiometriaRepository repository;

    public List<Biometria> findAll() {
        return repository.findAll();
    }

    public Biometria findById(Long id) {
        Optional<Biometria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Biometria insert(Biometria obj) {
        obj.setData(Instant.now());
        return repository.save(obj);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public Biometria update(Long id, Biometria newObj) {
        Biometria entity = findById(id);
        if (newObj.getBiometria() != null) {
            entity.setBiometria(newObj.getBiometria());
        }
        repository.save(entity);
        return entity;
    }

    public Biometria getMostRecent() {
        return repository.findMostRecent();
    }
}
