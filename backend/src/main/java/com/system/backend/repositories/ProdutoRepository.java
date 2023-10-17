package com.system.backend.repositories;

import com.system.backend.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.id = :#{#produto.getId} OR p.nome = :#{#produto.getNome} OR p.fornecedor = :#{#produto.getFornecedor} ")
    public List<Produto> findByCriteria(@Param("produto") Produto produto);

}
