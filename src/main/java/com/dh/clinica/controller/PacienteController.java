package com.dh.clinica.controller;
import com.dh.clinica.model.Endereco;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    public PacienteService pacienteService;

    @GetMapping("buscar")
    public List<Paciente> listarTodas(){
        return pacienteService.listarTodos();
    }
    @GetMapping("{id}")
    public Optional<Paciente> buscarPorId(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    @DeleteMapping("{id}")
    public void deletarPaciente (@PathVariable Integer id){
        pacienteService.excluir(id);
    }


    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente){
        return pacienteService.salvar(paciente);
    }

}
