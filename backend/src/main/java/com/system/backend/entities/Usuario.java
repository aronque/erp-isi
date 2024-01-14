package com.system.backend.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidade que representa um usuário mapeado no banco de dados.
 * @author aronque
 */
@Entity
@Table(name = "USUARIOS")
public class Usuario extends EntidadeBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO_ID")
    private Tipo tipo;

    @Transient
    private Usuario requestUser;

    public Usuario(){
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String name) {
        this.nome = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Tipo getTipo() {
        return tipo;
    }


    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(Usuario requestUser) {
        this.requestUser = requestUser;
    }

    public enum Tipo {

        FOO(0),
        FUNCIONARIO_ESTOQUE (1),
        FUNCIONARIO_ADM(2),
        FUNCIONARIO_COMUM(3),
        SUPORTE_SYS(4);

        public final int intValue;

        Tipo(int value) {
            this.intValue = value;
        }
    }
}
