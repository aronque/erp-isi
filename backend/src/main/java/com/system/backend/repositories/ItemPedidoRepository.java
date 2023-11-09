package com.system.backend.repositories;

import com.system.backend.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query("SELECT i FROM ItemPedido i WHERE i.id = :#{#item.getId} OR i.produto = :#{#item.getProduto} OR i.pedido = :#{#item.getPedido}")
    public List<ItemPedido> findByCriteria(@Param("item") ItemPedido item);

}
