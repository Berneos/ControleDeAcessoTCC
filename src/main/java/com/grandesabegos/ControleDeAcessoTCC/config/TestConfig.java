package com.grandesabegos.ControleDeAcessoTCC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.grandesabegos.ControleDeAcessoTCC.repositories.InstituicaoRepository;

@Configuration
@Profile("test")
//Database seeding
public class TestConfig implements CommandLineRunner{

	@Autowired
	private InstituicaoRepository InstituicaoRepository;

	@Override
	public void run(String... args) throws Exception {

		//Database seeding aqui
		
	}
	
}
