package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.PacienteRequest;
import com.dh.clinica.controller.dto.PacienteResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    public PacienteServiceImpl pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodas() throws ResourceNotFoundException {
        log.debug("Buscando lista de pacientes");
        List<PacienteResponse> pacientes = pacienteService.buscarTodos();
        if (!pacientes.isEmpty()) {
            log.debug("Lista de enderecos encontrada com sucesso!! - STATUS 200");
            return ResponseEntity.ok(pacientes);
        }else {
            throw new ResourceNotFoundException("Não foi possivel listar todos os pacientes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <PacienteResponse> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Realizando busca do paciente pelo seu ID");
        PacienteResponse pacienteResponse = pacienteService.buscarPorId(id);
        if (pacienteResponse != null) {
            log.debug("ID do paciente encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(pacienteResponse);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar Id do paciente");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<PacienteResponse> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        PacienteResponse paciente = pacienteService.buscarPorNome(nome);
        if (paciente != null) {
            log.debug("Nome do paciente encontrado com sucesso - STATUS 200");
            return ResponseEntity.ok(paciente);
        }else {
            throw new ResourceNotFoundException("Não foi possivel encontrar o nome do paciente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPaciente (@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Deletando paciente pelo seu ID");
        if (pacienteService.buscarPorId(id) != null){
            pacienteService.excluir(id);
            log.debug("ID excluido com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
        }else {
            throw new ResourceNotFoundException("Não foi possivel deletar paciente por ID");
        }
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@RequestBody PacienteRequest pacienteRequest) throws ResourceNotFoundException {
        log.debug("Salvando Paciente!");
        if (Objects.nonNull(pacienteRequest.getRg())){
            log.debug("Paciente salvo com sucesso - STATUS 200");
            return ResponseEntity.ok(pacienteService.salvar(pacienteRequest));
        }else {
            throw new ResourceNotFoundException("Não foi possivel salvar o paciente");
        }

    }

    @PutMapping
    public ResponseEntity<PacienteResponse> atualizar (@RequestBody PacienteRequest paciente) throws ResourceNotFoundException {
        log.debug("Atualizando pacientes");
        if (paciente.getRg() != null && pacienteService.buscarRg(paciente.getRg()) != null) {
            log.debug("Paciente foi atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(pacienteService.atualizar(paciente));
        }else {
            throw new ResourceNotFoundException("Não foi possivel atualizar todos os pacientes");
        }
    }

}
