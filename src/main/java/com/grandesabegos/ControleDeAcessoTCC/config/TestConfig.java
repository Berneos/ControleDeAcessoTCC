package com.grandesabegos.ControleDeAcessoTCC.config;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.grandesabegos.ControleDeAcessoTCC.entities.*;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;
import com.grandesabegos.ControleDeAcessoTCC.repositories.*;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private PessoaPadraoRepository pessoaPadraoRepository; // para pessoas genéricas

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public void run(String... args) throws Exception {

        Instant now = Instant.now();

        // --- INSTITUIÇÕES ---
        Instituicao i1 = new Instituicao(null, "Escola Estadual João Silva", "12345678000101", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Escola);
        Instituicao i2 = new Instituicao(null, "Academia PowerFit", "98765432000199", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Academia);
        Instituicao i3 = new Instituicao(null, "Empresa Tech Solutions", "11223344000166", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.Empresa);
        Instituicao i4 = new Instituicao(null, "Edifício Comercial Alpha Tower", "55667788000144", now,
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), Tipo.EdificioComercial);

        instituicaoRepository.saveAll(Set.of(i1, i2, i3, i4));

        // --- SETORES ---
        Setor setorTI = new Setor(null, "Tecnologia da Informação", new HashSet<>(), new HashSet<>());
        Setor setorRH = new Setor(null, "Recursos Humanos", new HashSet<>(), new HashSet<>());
        Setor setorSeg = new Setor(null, "Segurança", new HashSet<>(), new HashSet<>());

        setorRepository.saveAll(Set.of(setorTI, setorRH, setorSeg));

        // --- CARGOS ---
        Cargo cargoDev = new Cargo(null, "Desenvolvedor");
        Cargo cargoSuporte = new Cargo(null, "Suporte Técnico");
        Cargo cargoAnalistaRH = new Cargo(null, "Analista de RH");
        Cargo cargoRecrutador = new Cargo(null, "Recrutador");
        Cargo cargoVigilante = new Cargo(null, "Vigilante");
        Cargo cargoControladorAcesso = new Cargo(null, "Controlador de Acesso");

        cargoRepository.saveAll(Set.of(cargoDev, cargoSuporte, cargoAnalistaRH, cargoRecrutador, cargoVigilante, cargoControladorAcesso));

        // --- PESSOAS GENÉRICAS ---
        Pessoa p1 = new Pessoa(null, "Carlos da Silva", "11122233344", "11999999999", true, now,
                i1, "Rua das Flores, 123", null, 1001L);
        Pessoa p2 = new Pessoa(null, "Maria Oliveira", "55566677788", "11988888888", true, now,
                i2, "Av. Central, 456", null, 1002L);

        pessoaPadraoRepository.saveAll(Set.of(p1, p2));

        // --- FUNCIONÁRIOS ---
        Funcionario f1 = new Funcionario(null, "João Souza", "99988877766", "11977777777", true, now,
                i3, "Rua Alpha, 999", null, 1003L);
        f1.setSalario(3500.0);
        f1.setSetor(setorTI);
        f1.setCargo(cargoDev);

        Funcionario f2 = new Funcionario(null, "Ana Lima", "22233344455", "11966666666", true, now,
                i3, "Av. Beta, 111", null, 1004L);
        f2.setSalario(4200.0);
        f2.setSetor(setorRH);
        f2.setCargo(cargoAnalistaRH);

        Funcionario f3 = new Funcionario(null, "Pedro Santos", "33344455566", "11955555555", true, now,
                i3, "Rua Gama, 12", null, 1005L);
        f3.setSalario(2800.0);
        f3.setSetor(setorSeg);
        f3.setCargo(cargoVigilante);

        funcionarioRepository.saveAll(Set.of(f1, f2, f3));

        // --- ESTUDANTES ---
        Estudante e1 = new Estudante(null, "Lucas Pereira", "44455566677", "11944444444", true, now,
                i1, "Rua das Oliveiras, 50", null, 2001L);

        estudanteRepository.saveAll(Set.of(e1));
    }
}
