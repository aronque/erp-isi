package com.system.backend.repositories;

import com.system.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.id = :#{#endereco.getId} OR e.rua = :#{#endereco.getRua} OR e.numero = :#{#endereco.getNumero} OR e.bairro = :#{#endereco.getBairro} OR e.cidade = :#{#endereco.getCidade} OR e.estado = :#{#endereco.getEstado} OR e.cep = :#{#endereco.getCep}")
    public List<Endereco> findByCriteria(@Param("endereco") Endereco endereco);

}
