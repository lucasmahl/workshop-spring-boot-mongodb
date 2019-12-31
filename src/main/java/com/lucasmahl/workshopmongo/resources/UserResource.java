package com.lucasmahl.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmahl.workshopmongo.domain.User;
import com.lucasmahl.workshopmongo.dto.UserDTO;
import com.lucasmahl.workshopmongo.services.UserService;

//esta classe será um recurso rest (controlador rest), irá conversar com o serviço
@RestController
@RequestMapping(value = "/users") // caminho do endpoint
public class UserResource {

	// injetando o serviço
	@Autowired
	private UserService service;

	// @RequestMapping(method = RequestMethod.GET) pra dizer q o metodo será o
	// endpoint do caminho /users, declarado acima
	// ou só @GetMapping, q dá na mesma
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {// metodos q irá retornar uma lista de usuário
		// ResponseEntity, objeto q irá encapsular toda estrutura necessária pra
		// retornar resposta http
		// com possiveis cabeçalhos e possiveis erros

		List<User> list = service.findAll();// lista recebe o service

		// converte a lista de User pra lista de UserDTO
		List<UserDTO> listDto = list.stream()// transforma em uma stream q é uma coleção compativel com expressões lambda
				.map(x -> new UserDTO(x)) // pra cada objeto irá retornar um new UserDTO
				.collect(Collectors.toList()); // volta o stream pra List

		// list.addAll(Arrays.asList(maria, alex));// com Arrays.asList posso adicionar tudo de uma vez

		return ResponseEntity.ok().body(listDto);// ok é o metodo q irá instancia o ResponseEntity, já com o código de
												// resposta http, de sucesso
												//body pra definir qual será o corpo da resposta
	}
}
