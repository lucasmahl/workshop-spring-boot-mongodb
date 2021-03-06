package com.lucasmahl.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmahl.workshopmongo.domain.Post;
import com.lucasmahl.workshopmongo.resources.util.URL;
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
	
	//endpoint pra retornar a busca
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)//o caminho será posts/titlesearch, tmbm será get
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {//text por causa do parametro text informado na url
		//decodificando text
		text = URL.decodeParam(text);
		
		//declara lista de Post, recebendo service.findByTitle
		List<Post> list = service.findByTitle(text);
				
		return ResponseEntity.ok().body(list);
	}
	
	//endpoint fullSearch
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)//o caminho será posts/fullsearch, tmbm será get
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text, /*text por causa do parametro text informado na url*/
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		//decodificando text
		text = URL.decodeParam(text);
		
		//tratando as datas
		Date min = URL.convertDate(minDate, new Date(0L)); //new Date(0L) = data minima = 01/01/1970
		Date max = URL.convertDate(maxDate, new Date());  //new Date() = instante atual do sistema
		
		//declara lista de Post, recebendo service.findByTitle
		List<Post> list = service.fullSearch(text, min, max);
				
		return ResponseEntity.ok().body(list);
	}
}
