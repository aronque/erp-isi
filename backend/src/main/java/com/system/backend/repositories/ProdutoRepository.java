package com.system.backend.repositories;

import com.system.backend.entities.Produto;
import com.system.backend.repositories.customRepos.CustomProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA para operações em banco padronizadas relacionadas aos produtos
 * @author aronque
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, CustomProdutoRepository {
}
