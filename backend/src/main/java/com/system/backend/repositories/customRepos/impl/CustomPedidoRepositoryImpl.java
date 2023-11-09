package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Pedido;
import com.system.backend.entities.PedidoFornecedor;
import com.system.backend.repositories.customRepos.CustomPedidoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class CustomPedidoRepositoryImpl implements CustomPedidoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Pedido> findBy(Pedido pedido) {
        StringBuilder sb = new StringBuilder("FROM Pedido p ");
        Query query;

        if(pedido != null) {
            sb.append(" WHERE ");
            query = em.createQuery(setupQuery(pedido, sb));
        } else {
            query = em.createQuery(sb.toString());
        }

        List<?> result = query.getResultList();

        return (List<Pedido>) result;
    }

    private String setupQuery(Pedido pedido, StringBuilder sb) {
        boolean manyClauses = false;

        if(pedido.getId() != null) {
            return sb.append("id = ").
                    append(pedido.getId()).
                    toString();
        }

        if(pedido.getStatus() != null) {

            sb.append("status = '").
                    append(pedido.getStatus().toString()).
                    append("'");

            manyClauses = true;
        }

        if(pedido.getData() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("data = ").
                    append(pedido.getData());
        }

        if(pedido.getInstancia() != null) {

            if(manyClauses) sb.append(" AND ");
            manyClauses = true;

            sb.append("instancia = '").
                    append(pedido.getInstancia()).
                    append("'");

            if(pedido.getInstancia().equals(PedidoFornecedor.class.getSimpleName()) && pedido.getFornecedor() != null) {
                sb.append("fornecedor.id = ").
                        append(pedido.getFornecedor().getId());
            }
        }

        if(pedido.getUsuario() != null) {

            if(manyClauses) sb.append(" AND ");

            sb.append("usuario.id = ").
                    append(pedido.getUsuario().getId());

        }

        return sb.toString();
    }
}
