package com.system.backend.repositories;

import com.system.backend.entities.Produto;
import com.system.backend.repositories.customRepos.CustomProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, CustomProdutoRepository {
}
