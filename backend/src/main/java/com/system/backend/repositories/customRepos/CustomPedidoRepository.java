package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Pedido;

import java.util.List;

public interface CustomPedidoRepository {

    public List<Pedido> findBy(Pedido pedido);
}
