package com.dh.clinica.controller;
import com.dh.clinica.model.Endereco;
import com.dh.clinica.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/buscar")
    public List<Endereco> listarTodos(){
        return enderecoService.listarTodos();
    }

    @GetMapping("{id}")
    public Optional<Endereco> buscarPorId(@PathVariable Integer id){
        return enderecoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable Integer id) {
        enderecoService.excluir(id);
    }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco){
        return enderecoService.salvar(endereco);
    }


}
