package com.grandesabegos.ControleDeAcessoTCC.config;

import java.time.Instant;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jwt-secret}")
	private String jwtSecret;
    
    @Override
    public void run(String... args) throws Exception {



        System.out.println("💻 Configuração PROD inicial carregada: Instituição + Usuário Master");
        System.out.println("Secret é:" + jwtSecret);
        
    }
}
