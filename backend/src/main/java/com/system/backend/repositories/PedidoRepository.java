package com.system.backend.repositories;

import com.system.backend.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.id = :#{#pedido.getId} OR p.data = :#{#pedido.getData} OR p.status = :#{#pedido.getStatus} OR p.usuario = :#{#pedido.getUsuario}")
    public List<Pedido> findByCriteria(@Param("pedido") Pedido pedido);

}
