package com.system.backend.repositories;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.customRepos.CustomUsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, CustomUsuarioRepository {
}
