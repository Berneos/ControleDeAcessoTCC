package com.grandesabegos.ControleDeAcessoTCC.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PessoaPadraoRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class PessoaPadraoService extends PessoaService<Pessoa> {
    
    private final PessoaPadraoRepository repository;

    public PessoaPadraoService(PessoaPadraoRepository repository) {
        this.repository = repository;
    }

    public Page<Pessoa> filtrar(
            String nome, 
            Boolean ativo, 
            LocalDate dataInicio, 
            LocalDate dataFim, 
            Pageable pageable) {
        
        Specification<Pessoa> spec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (nome != null && !nome.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (ativo != null) {
                predicate = cb.and(predicate, cb.equal(root.get("ativo"), ativo));
            }

            if (dataInicio != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataCriacao"), dataInicio));
            }

            if (dataFim != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataCriacao"), dataFim));
            }

            return predicate;
        };

        return repository.findAll(spec, pageable);
    }

}
