package com.lucasmahl.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucasmahl.workshopmongo.domain.Post;

//usa spring-data
@Repository
public interface PostRepository extends MongoRepository<Post, String> {// MongoRepository<tipo da classe de dominio, tipo do Id>

}