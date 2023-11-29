package com.system.backend.repositories;

import com.system.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository JPA para operações em banco padronizadas relacionadas aos endereços dos fornecedores
 * @author aronque
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    /**
     * Busca endereços com base em filtro customizado de acordo com os dados fornecidos
     * @author aronque
     * @param endereco Objeto endereço contendo os dados fornecidos para o filtro
     * @return List<Endereco> Retorna uma lista contendo os endereços encontrados com base nos dados fornecidos
     */
    @Query("SELECT e FROM Endereco e WHERE e.id = :#{#endereco.getId} OR e.rua = :#{#endereco.getRua} OR e.numero = :#{#endereco.getNumero} OR e.bairro = :#{#endereco.getBairro} OR e.cidade = :#{#endereco.getCidade} OR e.estado = :#{#endereco.getEstado} OR e.cep = :#{#endereco.getCep}")
    public List<Endereco> findByCriteria(@Param("endereco") Endereco endereco);

}
