package com.grandesabegos.ControleDeAcessoTCC.config;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;

@Configuration
@Profile("test")
// Database seeding
public class TestConfig implements CommandLineRunner {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Override
    public void run(String... args) throws Exception {

        Instant now = Instant.now();

        Instituicao i1 = new Instituicao(
            null, 
            "Escola Estadual João Silva", 
            "12345678000101", 
            now, 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            Tipo.Escola
        );

        Instituicao i2 = new Instituicao(
            null, 
            "Academia PowerFit", 
            "98765432000199", 
            now, 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            Tipo.Academia
        );

        Instituicao i3 = new Instituicao(
            null, 
            "Empresa Tech Solutions", 
            "11223344000166", 
            now, 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            Tipo.Empresa
        );

        Instituicao i4 = new Instituicao(
            null, 
            "Edifício Comercial Alpha Tower", 
            "55667788000144", 
            now, 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            new HashSet<>(), 
            Tipo.EdificioComercial
        );

        instituicaoRepository.saveAll(Set.of(i1, i2, i3, i4));
    }
}
