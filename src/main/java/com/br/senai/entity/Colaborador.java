package com.br.senai.entity;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "Colaborador")
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_completo")
    @NotEmpty(message = "O nome completo do colaborador é obrigatório")
    @Pattern(regexp = "[A-zÀ-ú-ç\s]{2,50}",
            message = "O nome completo deve ter entre 2 e 50 caracteres literais.")
    private String nomeCompleto;

    @Column(name = "cpf")
    @NotEmpty(message = "O cpf do colaborador é obrigatório")
    @Pattern(regexp = "^([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})$",
            message = "O cpf deve possuir este formato 'XXX.XXX.XXX-XX', com apenas números inteiro positivo.")
    private String cpf;

    @Column(name = "dt_admissao")
    @NotNull(message = "A data de admissão do colaborador é obrigatório")
    private LocalDate dataDeAdmissao;


    @Column(name = "rg")
    @NotEmpty(message = "O rg do colaborador é obrigatório")
    @Pattern(regexp = "^([0-9]{2})\\.([0-9]{3})\\.([0-9]{3})$",
            message = "O rg deve possuir este formato 'XX.XXX.XXX', com apenas números inteiro positivo.")
    private String rg;

    @Column(name = "nome_da_mae")
    @NotEmpty(message = "O nome da mãe do colaborador é obrigatório")
    @Pattern(regexp = "[A-zÀ-ú-ç\s]{2,50}",
            message = "O nome da mãe deve ter entre 2 e 50 caracteres literais.")
    private String nomeDaMae;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "O usuário do colaborador é obrigatório")
    private Usuario usuario;
}
