package com.grandesabegos.ControleDeAcessoTCC.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.grandesabegos.ControleDeAcessoTCC.entities.Pessoa;

@NoRepositoryBean // Impede que Spring tente instanciar diretamente
public interface PessoaRepository<T extends Pessoa> extends JpaRepository<T, Long> {
    Optional<T> findByBiometria(Long biometria);
}
