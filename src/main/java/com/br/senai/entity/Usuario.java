package com.br.senai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.br.senai.enuns.EnumPerfil;

import lombok.Data;



@Data
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome_completo")
	@NotEmpty(message = "O nome completo do usuário é obrigatório")
	@Pattern(regexp = "[A-zÀ-ú\s]{2,50}",
			message = "O nome completo deve ter entre 2 e 50 caracteres literais ou números inteiros.")
	private String nomeCompleto;

	@Column(name = "login")
	@NotEmpty(message = "O login é obrigatório")
	@Pattern(regexp = "^[a-zA-ZÇ-ç0-9-*@#$%¨&*()-_\"']{2,20}$",
			message = "O login deve ter entre 2 e 20 caracteres.")
	private String login;

	@Column(name = "senha")
	@NotEmpty(message = "A senha é obrigatória")
	@Pattern(regexp = "[A-zÀ-ú0-9]{2,10}",
			message = "A senha deve ter entre 2 e 10 caracteres literais ou números inteiros.")
	private String senha;
	
	@Column(name = "perfil")
	@NotNull(message = "O perfil é obrigatória")
	@Enumerated(EnumType.STRING)
	private EnumPerfil perfil;
}
