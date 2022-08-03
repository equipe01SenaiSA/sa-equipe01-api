package com.br.senai.service;


import com.br.senai.entity.Colaborador;
import com.br.senai.entity.Usuario;
import com.br.senai.enuns.EnumPerfil;
import com.br.senai.exceptions.RegistroNaoEncontradoException;

import com.br.senai.repository.ColaboradoresRepository;
import com.br.senai.repository.UsuariosRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class ColaboradorService {

    @Autowired
    private ColaboradoresRepository repository;

    @Autowired
    private UsuariosRepository usuarioRepository;

    public Colaborador inserir(
            @Valid
            @NotNull(message = "O novo Colaborador é obrigatório")
            Colaborador novoColaborador) {
        Preconditions.checkArgument(novoColaborador.getId() == null,
                "O id do Colaborador deve ser nulo");
        novoColaborador.getUsuario().setNomeCompleto(novoColaborador.getNomeCompleto());
        novoColaborador.getUsuario().setPerfil(EnumPerfil.COLABORADOR);
        Colaborador colaboradorSalvo = repository.save(novoColaborador);
        return colaboradorSalvo;
    }

    public Colaborador alterar(
            @Valid
            @NotNull(message = "O Colaborador é obrigatório")
            Colaborador colaboradorSalvo) {
        Preconditions.checkArgument(colaboradorSalvo.getId() != null,
                "O id do Colaborador não deve ser nulo");
        Colaborador colaboradorAtualizado =
                repository.save(colaboradorSalvo);
        return colaboradorAtualizado;
    }

    public void excluirPor(
            @NotNull(message = "O id para exclusão não pode ser nulo")
            Integer id) {
        this.repository.deleteById(id);
    }


    public Colaborador buscarPor(
            @NotNull(message = "O id para busca não pode ser nulo")
            Integer id) {
        Colaborador colaboradorEncontrado = repository.buscarPor(id);
        if (colaboradorEncontrado == null) {
            throw new RegistroNaoEncontradoException(
                    "Não foi encontrado o Colaborador");
        }
        return colaboradorEncontrado;
    }


	public List<Colaborador> listarPor(
			@NotEmpty(message = "O nome é obrigatório")
			String nome){
		return repository.listarPor("%" + nome + "%");
	}

	
}
