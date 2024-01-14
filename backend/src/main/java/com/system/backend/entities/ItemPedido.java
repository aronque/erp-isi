package com.system.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um item de um pedido mapeado no banco de dados.
 * @author aronque
 */
@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido extends EntidadeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Pedido pedido;

    @JsonIgnoreProperties({"fornecedor", "quantidade"})
    @ManyToOne
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;


    public ItemPedido(){
    }


    @JsonIgnore
    public Pedido getPedido() {
        return pedido;
    }


    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    public Produto getProduto() {
        return produto;
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
