package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.ConsultaRequest;
import com.dh.clinica.controller.dto.ConsultaResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Consulta;
import com.dh.clinica.service.impl.ConsultaServiceImpl;
import com.dh.clinica.service.impl.DentistaServiceImpl;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    static final Logger log = Logger.getLogger(DentistaController.class);

    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private DentistaServiceImpl dentistaService;
    @Autowired
    private ConsultaServiceImpl consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponse> salvarConsulta (@RequestBody ConsultaRequest consultaRequest) throws ResourceNotFoundException {
        log.debug("Salvar consulta!");
        if (pacienteService.buscarPorId(consultaRequest.getPacienteId()) !=null &&
                dentistaService.buscarPorId(consultaRequest.getDentistaId()) !=null ) {
            return ResponseEntity.ok(consultaService.salvar(consultaRequest));
        } else {
            throw new ResourceNotFoundException("Não foi possivel cadastrar uma consulta");
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> buscarTodasConsultas() throws ResourceNotFoundException {
        List<ConsultaResponse> consultas = consultaService.buscarTodos();
        if (!consultas.isEmpty()) {
            log.debug("Lista de consultas encontrada com sucesso - STATUS 200");
            return ResponseEntity.ok(consultas);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar lista de consulta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizarConsulta(@RequestBody ConsultaRequest consulta) throws ResourceNotFoundException {
        ConsultaResponse consultaExistente = consultaService.buscarPorId(consulta.getId());
        if (consultaExistente != null) {
            return ResponseEntity.ok(consultaService.atualizar(consulta));
        } else {
            throw new ResourceNotFoundException("Não foi possivel atualizar consulta");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ConsultaResponse> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        ConsultaResponse consulta = consultaService.buscarPorNome(nome);
        if (consulta != null) {
            log.debug("Nome da consulta encontrado com sucesso! - STATUS 200");
            return ResponseEntity.ok(consulta);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encotrar nome da consulta");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirConsulta(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Exluindo consulta");
        if (consultaService.buscarPorId(id) != null) {
            consultaService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("Consulta deletada com sucesso");
        } else {
            throw new ResourceNotFoundException("Não foi possivel deletar consulta pelo ID");
        }
    }
}