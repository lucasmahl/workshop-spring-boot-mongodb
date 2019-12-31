package com.lucasmahl.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmahl.workshopmongo.domain.User;
import com.lucasmahl.workshopmongo.repository.UserRepository;
import com.lucasmahl.workshopmongo.services.exception.ObjectNotFoundException;

@Service // pra falar pro spring q essa classe será um serviço,q pode ser injetavel em outras classe
public class UserService {

	//o serviço tem q conversar com repositorio
	@Autowired //o proprio spring procurara a definição do objeto abaixo, e vai instanciar
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id: " + id + " não encontrado"));
	}
}
