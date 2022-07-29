package com.br.senai.repository;

import com.br.senai.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

	@Query(value =
			"SELECT u "
					+ "FROM Usuario u "
					+ "WHERE u.login = :login " +
					"AND u.senha = :senha ")
	Usuario buscarloginESenha(@Param("login") String login, @Param("senha") String senha);
	

}
