package com.lucasmahl.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmahl.workshopmongo.domain.User;

//esta classe será um recurso rest
@RestController
@RequestMapping(value = "/users") // caminho do endpoint
public class UserResource {

	// @RequestMapping(method = RequestMethod.GET) pra dizer q o metodo será o
	// endpoint do caminho /users
	// ou só @GetMapping, q dá na mesma
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {// metodos q irá retornar uma lista de usuário
		// ResponseEntity, objeto q irá encapsular toda estrutura necessária pra
		// retornar resposta http
		// com possiveis cabeçalhos e possiveis erros

		User maria = new User("1", "Maria", "maria@gmail.com");
		User alex = new User("1", "Alex", "alex@gmail.com");

		List<User> list = new ArrayList<User>();

		list.addAll(Arrays.asList(maria, alex));// com Arrays.asList posso adicionar tudo de uma vez

		return ResponseEntity.ok().body(list);// ok é o metodo q irá instancia o ResponseEntity, já com o código de
												// resposta http, de sucesso
		// body pra definir qual será o corpo da resposta
	}
}
