package com.system.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um produto mapeado no banco de dados.
 * @author aronque
 */
@Entity
@Table(name = "PRODUTOS")
public class Produto extends EntidadeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "NOME")
    private String nome;

    @JsonIgnoreProperties({"endereco"})
    @ManyToOne
    private Fornecedor fornecedor;

    @Column(name = "QNT_ESTOQUE")
    private Integer quantidade;

    @Column(name = "PRECO")
    private Double preco;

    @Transient
    private Usuario requestUser;

    public Produto(){
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public Fornecedor getFornecedor() {
        return fornecedor;
    }


    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public Double getPreco() {
        return preco;
    }


    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Usuario getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(Usuario requestUser) {
        this.requestUser = requestUser;
    }
}
