package com.br.senai.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity(name = "Cargo")
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao_curta",unique = true)
    @NotEmpty(message = "A descrição curta do cargo é obrigatória")
    @Pattern(regexp = "[A-zÀ-ú\s]{2,100}",
            message = "A  descrição curta deve ter entre 2 e 100 caracteres literais.")
    //unica
    private String descricaoCurta;

    @Column(name = "atribuicoes")
    private String atribuicoes;
}
