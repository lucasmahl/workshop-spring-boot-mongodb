package com.lucasmahl.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmahl.workshopmongo.domain.Post;
import com.lucasmahl.workshopmongo.repository.PostRepository;
import com.lucasmahl.workshopmongo.services.exception.ObjectNotFoundException;

@Service // pra falar pro spring q essa classe será um serviço, q pode ser injetavel em outras classe
public class PostService {

	// o serviço tem q conversar com repositorio
	@Autowired // o proprio spring procurara a definição do objeto abaixo, e vai instanciar
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}