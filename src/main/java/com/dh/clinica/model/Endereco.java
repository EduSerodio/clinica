package com.dh.clinica.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    //ATRIBUTOS
    private Integer id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;

}
