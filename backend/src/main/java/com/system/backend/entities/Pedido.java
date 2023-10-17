package com.system.backend.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PEDIDOS")
public abstract class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<ItemPedido> itens = new HashSet<>();

    @Column(name = "DATA_PEDIDO")
    private Date data;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_PEDIDO")
    private Status status;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Fornecedor fornecedor;

    public Pedido() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Set<ItemPedido> getItems() {
        return itens;
    }


    public void addItem(ItemPedido item) {
        this.itens.add(item);
    }


    public void addManyItems(Set<ItemPedido> items) {
        this.itens.addAll(items);
    }


    public Date getData() {
        return data;
    }


    public void setData(Date date) {
        this.data = date;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }


    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    public Double calculaTotal() {
        return itens.stream().mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade()).sum();
    }


    public enum Status {
        CRIADO (0),
        PROCESSANDO (1),
        PROCESSADO (2),
        FINALIZADO (3);

        public final int intValue;

        Status(int value) {
            this.intValue = value;
        }
    }

}
