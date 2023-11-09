package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.customRepos.CustomUsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@SuppressWarnings("ALL")
public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Usuario> findBy(Usuario usuario) {
        StringBuilder sb = new StringBuilder("FROM Usuario u ");
        Query query;

        if(usuario != null) {
            sb.append(" WHERE ");
            query = em.createQuery(setupQuery(usuario, sb));
        } else {
            query = em.createQuery(sb.toString());
        }

        List<?> result = query.getResultList();

        return (List<Usuario>) result;
    }

    private String setupQuery(Usuario usuario, StringBuilder sb) {
        boolean manyClauses = false;

        if(usuario.getId() != null) {
            return sb.append("id = ").
                    append(usuario.getId()).
                    toString();
        }

        if(usuario.getNome() != null) {

            sb.append("nome = '").
                    append(usuario.getNome()).
                    append("'");

            manyClauses = true;
        }

        if(usuario.getEmail() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("email = '").
                    append(usuario.getEmail()).
                    append("'");
        }

        if(usuario.getTipo() != null) {

            if(manyClauses) sb.append(" AND ");

            sb.append("tipo = ").
                    append(usuario.getTipo());
        }

        return sb.toString();
    }
}
