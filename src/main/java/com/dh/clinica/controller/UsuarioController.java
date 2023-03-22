package com.dh.clinica.controller;

import com.dh.clinica.model.Endereco;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.UsuarioService;
import com.dh.clinica.service.impl.UsuarioDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// ANOTAÇÕES REALIZADAS PARA INDICAR QUE ESSA CLASSE É UMA CONTROLLER E QUE ESTAMOS MAPEANDO ELA
// COM O PARÂMETRO ("/usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // serve para injetar as dependencias da classe "usuarioService"
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        usuarioService.excluir(id);
    }
    @GetMapping("/buscar/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id){
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }


}
