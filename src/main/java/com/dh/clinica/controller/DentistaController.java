package com.dh.clinica.controller;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController                       //ANOTAÇÃO PARA INDICAR QUE ESSA CLASSE É UMA CONTROLLER
@RequestMapping("/dentistas")         //ANOTAÇÃO QUE INDICA O MAPEAMENTO DA ROTA PELO /dentista)
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping("/buscar")
    public List<Dentista> listarTodos(){
        return dentistaService.listarTodos();
    }

    @GetMapping("{id}")
    public Optional<Dentista> buscarPorId(@PathVariable Integer id){
        return dentistaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarDentista(@PathVariable Integer id) {
        dentistaService.excluir(id);
    }

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista){
        return dentistaService.salvar(dentista);
    }



}
