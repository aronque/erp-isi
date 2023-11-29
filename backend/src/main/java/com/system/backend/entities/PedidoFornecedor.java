package com.system.backend.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um pedido de entrada de produtos mapeado no banco de dados.
 * @author aronque
 */
@Entity
public class PedidoFornecedor extends Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PedidoFornecedor() {
        super.setInstancia(this.getClass().getSimpleName());
    }
}
