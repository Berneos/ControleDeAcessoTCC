package com.grandesabegos.ControleDeAcessoTCC.services;

import org.springframework.stereotype.Service;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;
import com.grandesabegos.ControleDeAcessoTCC.repositories.PessoaPadraoRepository;

@Service
public class PessoaPadraoService extends PessoaService<Pessoa> {
    
    public PessoaPadraoService(PessoaPadraoRepository repository) {
        this.repository = repository;
    }
}
