package com.lucasmahl.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucasmahl.workshopmongo.domain.Post;
import com.lucasmahl.workshopmongo.domain.User;
import com.lucasmahl.workshopmongo.dto.AuthorDTO;
import com.lucasmahl.workshopmongo.repository.PostRepository;
import com.lucasmahl.workshopmongo.repository.UserRepository;

//Operação de instanciação da base de dados

@Configuration //pro Spring entender q isto é uma configuração
public class Instantiation implements CommandLineRunner {

	// injetando UserRepository
	@Autowired
	private UserRepository userRepository;

	// injetando PostRepository
	@Autowired
	private PostRepository postRepository;
		
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll(); // vai ficar zerada a coleção no MongoDB
		postRepository.deleteAll(); // vai ficar zerada a coleção no MongoDB

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //horario de greenwtch
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		//salva primeiro os usuário pra q eles tenham id proprio, pra serem usados nos posts, no new AuthorDTO
		userRepository.saveAll(Arrays.asList(maria, alex, bob));// Arrays.asList aceita vários argumentos
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo, abraços.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));// Arrays.asList aceita vários argumentos
	}

}
