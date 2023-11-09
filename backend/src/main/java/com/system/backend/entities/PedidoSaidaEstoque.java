package com.system.backend.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class PedidoSaidaEstoque extends Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PedidoSaidaEstoque() {
        super.setInstancia(this.getClass().getSimpleName());
    }
}
