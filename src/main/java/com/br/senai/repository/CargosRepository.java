package com.br.senai.repository;

import com.br.senai.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends JpaRepository<Cargo, Integer> {

    @Query(value =
            "SELECT c "
                    + "FROM Cargo c "
                    + "WHERE c.id = :id ")
    Cargo buscarPor(@Param("id") Integer id);
}
