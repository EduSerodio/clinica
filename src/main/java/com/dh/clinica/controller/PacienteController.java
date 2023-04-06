package com.dh.clinica.controller;
import com.dh.clinica.controller.dto.PacienteResponse;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        log.debug("Realizando busca do paciente pelo seu ID");
        Paciente paciente = pacienteService.buscarPorId(id).orElse(null);
        if (paciente != null) {
            log.debug("ID do paciente foi encontrado com sucesso!! - STATUS 200");
            return ResponseEntity.ok(Optional.of(paciente));
        }else {
            throw new ResourceNotFoundException("Não foi possivel buscar paciente por ID");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Paciente> buscarPorNome (@PathVariable String nome) throws ResourceNotFoundException {
        Paciente paciente = pacienteService.buscarPorNome(nome).orElse(null);
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
        if (pacienteService.buscarPorId(id).isPresent()){
            pacienteService.excluir(id);
            log.debug("ID excluido com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
        }else {
            throw new ResourceNotFoundException("Não foi possivel deletar paciente por ID");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        log.debug("Realizando o cadastro de Paciente");
        if (Objects.nonNull(paciente.getRg())){
            log.debug("Paciente cadastrado com sucesso - STATUS 200");
            return ResponseEntity.ok(pacienteService.salvar(paciente));
        }else {
            throw new ResourceNotFoundException("Não foi possivel listar todos os pacientes");
        }

    }

    @PutMapping
    public ResponseEntity<Paciente> atualizar (@RequestBody Paciente paciente) throws ResourceNotFoundException {
        log.debug("Atualizando pacientes");
        if (paciente.getId() != null && pacienteService.buscarPorId(paciente.getId()).isPresent()) {
            log.debug("Paciente foi atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(paciente);
        }else {
            throw new ResourceNotFoundException("Não foi possivel atualizar todos os pacientes");
        }
    }

}
