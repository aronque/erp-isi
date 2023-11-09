package com.system.backend.repositories;

import com.system.backend.entities.Pedido;
import com.system.backend.repositories.customRepos.CustomPedidoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, CustomPedidoRepository {

}
