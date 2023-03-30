package com.dh.clinica.model;
import jakarta.persistence.*;
import lombok.*;

// ANOTAÇÕES DE GETTERS E SETTERS E TODOS OS CONSTRUTORES, PARA TER UM CÓDIGO MAIS LIMPO
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
