package com.dh.clinica.controller;

import com.dh.clinica.model.Consulta;
import com.dh.clinica.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private PacienteService pacienteService;

//    @PostMapping
//    public ResponseEntity<Consulta> cadastrar(@RequestBody Consulta consulta) {
//        if (pacienteService.buscarPorId(consulta.getPaciente().getId()).is
//
//
//
//    }


}
