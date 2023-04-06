package com.dh.clinica.controller;
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
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        ResponseEntity<Consulta> response;
        if (pacienteService.buscarPorId(consulta.getPaciente().getId()).isPresent() &&
                dentistaService.buscarPorId(consulta.getDentista().getId()).isPresent()) {
            response = ResponseEntity.ok(consultaService.salvar(consulta));
        } else {
            throw new ResourceNotFoundException("Não foi possivel cadastrar uma consulta");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> buscarTodasConsultas() throws ResourceNotFoundException {
        List<Consulta> consultas = consultaService.buscarTodos();
        if (!consultas.isEmpty()) {
            log.debug("Lista de consulta encontrada com sucesso - STATUS 200");
            return ResponseEntity.ok(consultas);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar lista de consulta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) throws ResourceNotFoundException {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consulta.setId(id);
            return ResponseEntity.ok(consultaService.atualizar(consulta));
        } else {
            throw new ResourceNotFoundException("Não foi possivel atualizar consulta");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Consulta> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        Consulta consulta = consultaService.buscarPorNome(nome).orElse(null);
        if (consulta != null) {
            log.debug("Nome da consulta encontrado com sucesso! - STATUS 200");
            return ResponseEntity.ok(consulta);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encotrar nome da consulta");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consultaService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("Consulta deletada com sucesso");
        } else {
            throw new ResourceNotFoundException("Não foi possivel deletar consulta pelo ID");
        }
    }
}