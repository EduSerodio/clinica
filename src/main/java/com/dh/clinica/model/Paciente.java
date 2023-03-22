package com.dh.clinica.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    //ATRIBUTOS
    private Integer id;
    private String nome;
    private String sobrenome;
    private String rg;
    private Date dataNascimento;
    private Endereco endereco;

}
