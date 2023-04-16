package com.dh.clinica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   //ignorar atributos da classe model usuarios
@Builder
public class PacienteResponse {

    private String nome;
    private String sobrenome;
    private String rg;

}
