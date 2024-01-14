package com.system.backend.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um endereço mapeado no banco de dados.
 * @author aronque
 */
@Entity
@Table(name = "ENDERECOS")
public class Endereco extends EntidadeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO_UF")
    private String estado;

    @Column(name = "CEP")
    private String cep;

    public Endereco() {
    }


    public String getRua() {
        return rua;
    }


    public void setRua(String rua) {
        this.rua = rua;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getBairro() {
        return bairro;
    }


    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getCidade() {
        return cidade;
    }


    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }

}
