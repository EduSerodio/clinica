package com.dh.clinica.model;
import jakarta.persistence.*;
import lombok.*;

// ANOTAÇÕES DE GETTERS E SETTERS E TODOS OS CONSTRUTORES, PARA TER UM CÓDIGO MAIS LIMPO
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Entity
@Table(name = "tb_dentista")
public class Dentista {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sobrenome;
    private String matricula;

    public Dentista(Integer id, String nome, String sobrenome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
    }

    public Dentista(String nome, String sobrenome, String matricula) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
    }

    public Dentista() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
