package com.dh.clinica.controller.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class PacienteRequest {

    private String nome;
    private String sobrenome;
    private String rg;
    private Date dataNascimento;

}
