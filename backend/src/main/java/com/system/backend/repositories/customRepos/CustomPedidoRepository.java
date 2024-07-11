package com.system.backend.repositories.customRepos;

import com.system.backend.entities.EntidadeBase;
import com.system.backend.entities.Pedido;

import java.util.List;

/**
 * Repository customizado para operações em banco relacionadas a entidade Pedido
 * @author aronque
 */
public interface CustomPedidoRepository {


    /**
     * Filtro customizado com base nos dados fornecidos
     * @author aronque
     * @param pedido Objeto pedido com dados fornecidos para filtro customizado
     * @return List<Pedido> Lista contendo os pedidos encontrados com base nos dados fornecidos
     */
    public List<EntidadeBase> findBy(Pedido pedido);
}
