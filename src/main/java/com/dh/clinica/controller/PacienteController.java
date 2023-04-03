package com.dh.clinica.controller;
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
    public ResponseEntity<List<Paciente>> listarTodas(){
        log.debug("Buscando lista de pacientes");
        List<Paciente> pacientes = pacienteService.buscarTodos();
        if (!pacientes.isEmpty()) {
            log.debug("Lista de enderecos encontrada com sucesso!! - STATUS 200");
            return ResponseEntity.ok(pacientes);
        }else {
            log.debug("Lista de pacientes nao foi encontrada - STATUS 404");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Integer id){
        log.debug("Realizando busca do paciente pelo seu ID");
        Paciente paciente = pacienteService.buscarPorId(id).orElse(null);
        if (paciente != null) {
            log.debug("ID do paciente foi encontrado com sucesso!! - STATUS 200");
            return ResponseEntity.ok(Optional.of(paciente));
        }else {
            log.debug("ID do paciente nao foi encontrado - STATUS 404");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Paciente> buscarPorNome (@PathVariable String nome) {
        return ResponseEntity.ok(pacienteService.buscarPorNome(nome).orElse(null) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPaciente (@PathVariable Integer id){
        log.debug("Deletando paciente pelo seu ID");
        if (pacienteService.buscarPorId(id).isPresent()){
            pacienteService.excluir(id);
            log.debug("ID excluido com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
        log.debug("Realizando o cadastro de Paciente");
        if (Objects.nonNull(paciente.getRg())){
            log.debug("Paciente cadastrado com sucesso - STATUS 200");
            return ResponseEntity.ok(pacienteService.salvar(paciente));
        }else {
            log.debug("Paciente nao encontrado - STATUS 404");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping
    public ResponseEntity<Paciente> atualizar (@RequestBody Paciente paciente){
        log.debug("Atualizando pacientes");
        if (paciente.getId() != null && pacienteService.buscarPorId(paciente.getId()).isPresent()) {
            log.debug("Paciente foi atualizado com sucesso - STATUS 200");
            return ResponseEntity.ok(paciente);
        }else {
            log.debug("Paciente nao foi atualizado - STATUS 404");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
