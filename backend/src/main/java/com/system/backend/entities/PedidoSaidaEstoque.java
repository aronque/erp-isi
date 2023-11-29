package com.system.backend.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um pedido de retirada de produtos mapeado no banco de dados.
 * @author aronque
 */
@Entity
public class PedidoSaidaEstoque extends Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PedidoSaidaEstoque() {
        super.setInstancia(this.getClass().getSimpleName());
    }
}
