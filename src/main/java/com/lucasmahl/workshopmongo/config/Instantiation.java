package com.lucasmahl.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucasmahl.workshopmongo.domain.User;
import com.lucasmahl.workshopmongo.repository.UserRepository;

//Operação de instanciação da base de dados

@Configuration //pro Spring entender q isto é uma configuração
public class Instantiation implements CommandLineRunner {

	// injetando UserRepository
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll(); // vai ficar zerada a coleção no MongoDB

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));// Arrays.asList aceita vários argumentos

	}

}
