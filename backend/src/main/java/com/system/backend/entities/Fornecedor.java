package com.system.backend.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * Entidade que representa um fornecedor mapeado no banco de dados.
 * @author aronque
 */
@Entity
@Table(name = "FORNECEDORES")
public class Fornecedor extends EntidadeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "CONTATO")
    private String contato;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;

    @Transient
    private Usuario requestUser;

    public Fornecedor() {
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCnpj() {
        return cnpj;
    }


    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public String getContato() {
        return contato;
    }


    public void setContato(String contato) {
        this.contato = contato;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(Usuario requestUser) {
        this.requestUser = requestUser;
    }
}
