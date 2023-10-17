package com.system.backend.repositories;

import com.system.backend.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query("SELECT f FROM Fornecedor f WHERE f.id = :#{#fornecedor.getId} OR f.nome = :#{#fornecedor.getNome} OR f.cnpj = :#{#fornecedor.getCnpj} OR f.endereco = :#{#fornecedor.getEndereco}")
    public List<Fornecedor> findByCriteria(@Param("fornecedor") Fornecedor fornecedor);

}
