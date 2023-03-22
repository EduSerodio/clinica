package com.dh.clinica.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ANOTAÇÕES DE GETTERS E SETTERS E TODOS OS CONSTRUTORES, PARA TER UM CÓDIGO MAIS LIMPO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dentista {

    //ATRIBUTOS
    private Integer id;
    private String nome;
    private String sobrenome;
    private String matricula;


}
