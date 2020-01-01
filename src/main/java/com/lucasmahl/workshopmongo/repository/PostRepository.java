package com.lucasmahl.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucasmahl.workshopmongo.domain.Post;

//usa spring-data
@Repository //camada de acesso a dados, permite salvar, recuperar, atualizar, deletar...
public interface PostRepository extends MongoRepository<Post, String> {// MongoRepository<tipo da classe de dominio, tipo do Id>

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") // @Query busca como json, e não como string // ?0 = title // 'i' pra ignorar maiusculas e minusculas
	List<Post> searchTitle(String text);
	
	//retorna lista de post q contenha no titulo a String informada na busca
	List<Post> findByTitleContainingIgnoreCase(String text);//IgnoreCase pra não ser casesensitive
}
