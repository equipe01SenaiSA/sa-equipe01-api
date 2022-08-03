package com.br.senai.repository;


import com.br.senai.entity.Colaborador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradoresRepository extends JpaRepository<Colaborador, Integer> {


    @Query(value =
            "SELECT c "
                    + "FROM Colaborador c "
                    + "WHERE c.id = :id ")
    Colaborador buscarPor(@Param("id") Integer id);
    
    @Query(value =
            "SELECT c "
                    + "FROM Colaborador c "
                    + "WHERE Upper(c.nomeCompleto) LIKE Upper(:nomeCompleto)")
    List<Colaborador> listarPor(@Param("nomeCompleto") String nomeCompleto);

}
