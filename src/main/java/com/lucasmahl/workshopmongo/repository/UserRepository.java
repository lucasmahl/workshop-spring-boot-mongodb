package com.lucasmahl.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucasmahl.workshopmongo.domain.User;

//usa spring-data
@Repository //camada de acesso a dados, permite salvar, recuperar, atualizar, deletar...
public interface UserRepository extends MongoRepository<User, String>{//MongoRepository<tipo da classe de dominio, tipo do Id>

}
