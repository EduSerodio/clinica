package com.dh.clinica.controller.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)    //ignorar atributos da classe model usuarios
@Builder
public class DentistaResponse {

    private String nome;
    private String sobrenome;

}
