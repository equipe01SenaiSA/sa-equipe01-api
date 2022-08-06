package com.br.senai.service;

import com.br.senai.entity.Cargo;
import com.br.senai.exceptions.RegistroNaoEncontradoException;
import com.br.senai.repository.CargosRepository;
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
public class CargoService {

    @Autowired
    private CargosRepository repository;

    public Cargo inserir(
            @Valid
            @NotNull(message = "O novo Cargo é obrigatório")
            Cargo novoCargo) {
        Preconditions.checkArgument(novoCargo.getId() == null,
                "O id do Cargo deve ser nulo");
        Cargo cargoSalvo = repository.save(novoCargo);
        return cargoSalvo;
    }

    public Cargo alterar(
            @Valid
            @NotNull(message = "O Cargo é obrigatório")
            Cargo cargoSalvo) {
        Preconditions.checkArgument(cargoSalvo.getId() != null,
                "O id do cargo não deve ser nulo");
        Cargo cargoAtualizado =
                repository.save(cargoSalvo);
        return cargoAtualizado;
    }

    public void excluirPor(
            @NotNull(message = "O id para exclusão não pode ser nulo")
            Integer id) {
        this.repository.deleteById(id);
    }


    public Cargo buscarPor(
            @NotNull(message = "O id para busca não pode ser nulo")
            Integer id) {
        Cargo cargoEncontrado = repository.buscarPor(id);
        if (cargoEncontrado == null) {
            throw new RegistroNaoEncontradoException(
                    "Não foi encontrado o Cargo");
        }
        return cargoEncontrado;
    }


	public List<Cargo> listarPor(
			@NotEmpty(message = "A descrição curta é obrigatória")
			String descricaoCurta){
		return repository.listarPor("%" + descricaoCurta + "%");
	}

	public List<Cargo> listAllCargos(){
		return repository.findAll();
	} 
}
