package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Fornecedor;
import com.system.backend.repositories.customRepos.CustomFornecedorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class CustomFornecedorRepositoryImpl implements CustomFornecedorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Fornecedor> findBy(Fornecedor fornecedor) {
        StringBuilder sb = new StringBuilder("FROM Fornecedor f ");
        Query query = null;

        if(fornecedor != null) {
            sb.append(" WHERE ");
            query = em.createQuery(setupQuery(fornecedor, sb));
        } else {
            query = em.createQuery(sb.toString());
        }

        List<?> result = query.getResultList();

        return (List<Fornecedor>) result;
    }

    private String setupQuery(Fornecedor fornecedor, StringBuilder sb) {
        boolean manyClauses = false;

        if(fornecedor.getId() != null) {
            return sb.append("id = ").
                    append(fornecedor.getId()).
                    toString();
        }

        if(fornecedor.getNome() != null) {

            sb.append("nome = '").
                    append(fornecedor.getNome()).
                    append("'");

            manyClauses = true;
        }

        if(fornecedor.getCnpj() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("cnpj = '").
                    append(fornecedor.getCnpj()).
                    append("'");
        }

        if(fornecedor.getContato() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("contato = '").
                    append(fornecedor.getContato()).
                    append("'");
        }

        if(fornecedor.getEndereco() != null) {

            if(manyClauses) sb.append(" AND ");

            sb.append("endereco = ").
                    append(fornecedor.getEndereco().getId());

        }

        return sb.toString();
    }
}
