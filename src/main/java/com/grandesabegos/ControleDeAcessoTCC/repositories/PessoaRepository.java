package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

@NoRepositoryBean // Impede que o Spring tente instanciar diretamente
public interface PessoaRepository<T extends Pessoa> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    Optional<T> findByBiometriaAndEmpresaId(String biometria, Long empresaId); // empresaId é o id da instituição
}

