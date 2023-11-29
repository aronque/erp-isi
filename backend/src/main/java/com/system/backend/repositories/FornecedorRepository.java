package com.system.backend.repositories;

import com.system.backend.entities.Fornecedor;
import com.system.backend.repositories.customRepos.CustomFornecedorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA para operações em banco padronizadas relacionadas aos fornecedores
 * @author aronque
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, CustomFornecedorRepository {
}
