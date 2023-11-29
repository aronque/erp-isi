package com.system.backend.repositories;

import com.system.backend.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA para operações em banco padronizadas relacionadas aos itens de um pedido
 * @author aronque
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
