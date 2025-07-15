package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.stereotype.Repository;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

@Repository
public interface PessoaPadraoRepository extends PessoaRepository<Pessoa> {}
