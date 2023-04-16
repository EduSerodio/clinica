package com.dh.clinica.controller.dto;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)    //ignorar atributos da classe model usuarios
@Builder
public class ConsultaRequest {

    private Integer id;
    private Integer pacienteId;
    private Integer dentistaId;
    private Date date;

}
