package com.dh.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    private Integer id;
    private Paciente paciente;
    private Dentista dentista;
    private Date date;


}
