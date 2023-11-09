package com.system.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "PEDIDOS")
public abstract class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "DATA_PEDIDO")
    private Date data;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_PEDIDO")
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties({"senha", "email", "tipo"})
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties({"cnpj", "contato", "endereco"})
    private Fornecedor fornecedor;

    @JsonIgnore
    @Column(name = "DTYPE", insertable = false, updatable = false)
    private String instancia;

    public Pedido() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public List<ItemPedido> getItems() {
        return itens;
    }


    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }


    public void addItem(ItemPedido item) {
        this.itens.add(item);
    }


    public void addManyItems(List<ItemPedido> items) {
        if(items != null) this.itens.addAll(items);
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


    public String getInstancia() {
        return this.instancia;
    }


    public void setInstancia(String instancia) {
        this.instancia = instancia;
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
