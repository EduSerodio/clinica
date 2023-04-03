package com.dh.clinica.controller;
import com.dh.clinica.model.Consulta;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.ConsultaServiceImpl;
import com.dh.clinica.service.impl.DentistaServiceImpl;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private DentistaServiceImpl dentistaService;
    @Autowired
    private ConsultaServiceImpl consultaService;

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) {
        ResponseEntity<Consulta> response;
        if (pacienteService.buscarPorId(consulta.getPaciente().getId()).isPresent() &&
                dentistaService.buscarPorId(consulta.getDentista().getId()).isPresent()) {
            response = ResponseEntity.ok(consultaService.salvar(consulta));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> buscarTodasConsultas() {
        return ResponseEntity.ok(consultaService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consulta.setId(id);
            return ResponseEntity.ok(consultaService.atualizar(consulta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Consulta> buscarPorNome (@PathVariable String nome) {
        return ResponseEntity.ok(consultaService.buscarPorNome(nome).orElse(null) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Integer id) {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consultaService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("Consulta deletada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}