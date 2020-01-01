package com.lucasmahl.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmahl.workshopmongo.domain.Post;
import com.lucasmahl.workshopmongo.services.PostService;

//esta classe será um recurso rest (controlador rest), irá conversar com o serviço
@RestController
@RequestMapping(value = "/posts") // caminho do endpoint
public class PostResource {

	// injetando o serviço
	@Autowired
	private PostService service; //dependencia

	//metodo q ira retornar o post, do findById
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)//o caminho será posts/{id}, tmbm será get
	public ResponseEntity<Post> findById(@PathVariable String id) {//@PathVariable pra falar q o id passado tem q casar com id do caminho

		Post obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);//obj convertido pra userDTo
	}
}
