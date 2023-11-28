package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.customRepos.CustomUsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class CustomUsuarioRepositoryImpl implements CustomUsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    private boolean login = false;

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


    @Override
    public Usuario login(String email, String senha) {
        login = true;
        Usuario usuarioAux = new Usuario();
        StringBuilder sb = new StringBuilder(" FROM Usuario u WHERE ");
        Query query;

        usuarioAux.setEmail(email);
        usuarioAux.setSenha(senha);

        query = em.createQuery(setupQuery(usuarioAux, sb));
        List<?> resultList = query.getResultList();

        return (Usuario) (!resultList.isEmpty() ? resultList.get(0) : null);
    }


    @Override
    public Long hasPermission(Long id) {


        return null;
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

        if(login) {
            if(manyClauses) sb.append(" AND ");

            sb.append("senha = '").
                    append(usuario.getSenha()).
                    append("'");
        }

        return sb.toString();
    }
}
