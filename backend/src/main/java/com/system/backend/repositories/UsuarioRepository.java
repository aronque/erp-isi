package com.system.backend.repositories;

import com.system.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.id = :#{#usuario.getId} OR u.nome = :#{#usuario.getNome} OR u.email = :#{#usuario.getEmail} OR u.tipo = :#{#usuario.getTipo}")
    public List<Usuario> findByCriteria(@Param("usuario") Usuario usuario);

}
