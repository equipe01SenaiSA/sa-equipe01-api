package com.br.senai.service;


import com.br.senai.entity.Usuario;
import com.br.senai.exceptions.RegistroNaoEncontradoException;
import com.br.senai.repository.UsuariosRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;




@Service
@Validated
public class UsuarioService {
	
	@Autowired
	private UsuariosRepository repository;

	public Usuario buscarLoginESenha(
			@NotNull(message = "O login e senha para busca não pode ser nulo")
			String login,
			String senha) {
		Usuario UsuarioEncontrado = repository.buscarloginESenha(login, senha);
		if (UsuarioEncontrado == null) {
			throw new RegistroNaoEncontradoException(
					"Não foi encontrado o Usuario");
		}
		return UsuarioEncontrado;
	}

}
