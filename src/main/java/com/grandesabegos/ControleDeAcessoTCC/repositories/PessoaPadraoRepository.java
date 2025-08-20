package com.grandesabegos.ControleDeAcessoTCC.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

@Repository
public interface PessoaPadraoRepository extends PessoaRepository<Pessoa>,JpaSpecificationExecutor<Pessoa> {}
