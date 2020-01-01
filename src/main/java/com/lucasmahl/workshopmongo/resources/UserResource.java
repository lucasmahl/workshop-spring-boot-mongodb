package com.lucasmahl.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	//metodo q ira retornar o userdto, do findById
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)//o caminho será users/{id}, tmbm será get
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {//@PathVariable pra falar q o id passado tem q casar com id do caminho

		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));//obj convertido pra userDTo
	}
	
	//endpoint pra inserir novo usuário
	@RequestMapping(method = RequestMethod.POST)//o caminho será /users, porém é post //@RequestMapping(method = RequestMethod.POST) = @postmapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {//Void pq a inserção retornará um objeto vazio //@RequestBody pra q o endpoint aceite o objeto

		User obj = service.fromDTO(objDto); //converte objDto pra usuário
		obj = service.insert(obj);
		
		//retorna uma resposta vazia, mas com cabeçalho com a url do novo recurso criado, como boa prática
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build(); //created retorna o cód 201, de resposta http, qndo é criado um novo recurso
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		//qndo uma operação não tem q retornar nada, será uma operação com o cód. 204, usando .noContent().build()
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {

		User obj = service.fromDTO(objDto);
		obj.setId(id);//pra garantir q o objeto terá o id da requisição
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();

	}
}
