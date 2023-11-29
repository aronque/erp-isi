package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Usuario;

import java.util.List;

/**
 * Repository customizado para operações em banco relacionadas a entidade Usuario
 * @author aronque
 */
public interface CustomUsuarioRepository {

    /**
     * Filtro customizado com base nos dados fornecidos
     * @author aronque
     * @param usuario Objeto usuario com dados fornecidos para filtro customizado
     * @return List<Usuario> Lista contendo os usuarios encontrados com base nos dados fornecidos
     */
    public List<Usuario> findBy(Usuario usuario);

}
