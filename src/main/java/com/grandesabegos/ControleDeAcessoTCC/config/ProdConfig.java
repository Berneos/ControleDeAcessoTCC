package com.grandesabegos.ControleDeAcessoTCC.config;

import java.time.Instant;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.grandesabegos.ControleDeAcessoTCC.entities.Instituicao;
import com.grandesabegos.ControleDeAcessoTCC.entities.Usuario;
import com.grandesabegos.ControleDeAcessoTCC.entities.enums.Tipo;
import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;
import com.grandesabegos.ControleDeAcessoTCC.repositories.UsuarioRepository;

@Configuration
@Profile("prod")
public class ProdConfig implements CommandLineRunner {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Instant now = Instant.now();

        // --- INSTITUIÇÃO INICIAL ---
        Instituicao matriz = new Instituicao(
                null,
                "Instituição Matriz",
                "00000000000001",
                now,
                new HashSet<>(), // setores
                new HashSet<>(), // usuários
                new HashSet<>(), // planos
                new HashSet<>(), // assinantes
                Tipo.Empresa
        );

        instituicaoRepository.save(matriz);

        // --- USUÁRIO MASTER ---
        Usuario master = new Usuario(
                null,
                "Master do Sistema",
                "00011122233",
                "11900011122",
                true,
                now,
                matriz,
                "master",
                "master@sistema.com",
                passwordEncoder.encode("senhaMaster123") // 🔑 coloque uma senha segura
        );
        master.setIsMaster(true); // define como master

        usuarioRepository.save(master);

        System.out.println("💻 Configuração PROD inicial carregada: Instituição + Usuário Master");
    }
}
