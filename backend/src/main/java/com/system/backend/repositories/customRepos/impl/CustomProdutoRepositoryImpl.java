package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Produto;
import com.system.backend.repositories.customRepos.CustomProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class CustomProdutoRepositoryImpl implements CustomProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> findBy(Produto produto) {
        StringBuilder sb = new StringBuilder("FROM Produto p ");
        Query query;

        if(produto != null) {
            sb.append(" WHERE ");
            query = em.createQuery(setupQuery(produto, sb));
        } else {
            query = em.createQuery(sb.toString());
        }

        List<?> result = query.getResultList();

        return (List<Produto>) result;
    }

    private String setupQuery(Produto produto, StringBuilder sb) {
        boolean manyClauses = false;

        if(produto.getId() != null) {
            return sb.append("id = ").
                    append(produto.getId()).
                    toString();
        }

        if(produto.getNome() != null) {

            sb.append("nome = '").
                    append(produto.getNome()).
                    append("'");

            manyClauses = true;
        }

        if(produto.getFornecedor() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("fornecedor.id = ").
                    append(produto.getFornecedor().getId());
        }

        if(produto.getPreco() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("preco = '").
                    append(produto.getPreco()).
                    append("'");
        }

        if(produto.getQuantidade() != null) {

            if(manyClauses) sb.append(" AND ");

            sb.append("quantidade = ").
                    append(produto.getQuantidade());

        }

        return sb.toString();
    }
}
