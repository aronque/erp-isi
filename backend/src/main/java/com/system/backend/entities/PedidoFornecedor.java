package com.system.backend.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class PedidoFornecedor extends Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PedidoFornecedor() {
        super.setInstancia(this.getClass().getSimpleName());
    }
}
