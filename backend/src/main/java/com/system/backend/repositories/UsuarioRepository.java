package com.system.backend.repositories;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.customRepos.CustomUsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA para operações em banco padronizadas relacionadas aos usuários
 * @author aronque
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, CustomUsuarioRepository {
}
