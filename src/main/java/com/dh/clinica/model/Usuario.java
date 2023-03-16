package com.dh.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// ANOTAÇÕES DE GETTERS E SETTERS E TODOS OS CONSTRUTORES, PARA TER UM CÓDIGO MAIS LIMPO
@Getter
@Setter
@AllArgsConstructor
public class Usuario {

    // ATRIBUTOS
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String nivelAcesso;

    // CONSTRUTOR SEM PARÂMETROS
    public Usuario(){
    }

    // CONSTRUTOR SEM O ATRIBUTO "ID"
    public Usuario(String nome, String email, String senha, String nivelAcesso) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }
}
